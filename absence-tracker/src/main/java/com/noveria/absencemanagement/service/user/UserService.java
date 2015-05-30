package com.noveria.absencemanagement.service.user;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userDAO.findUserByUsername(username);

            List<GrantedAuthority> authorities =
                    buildUserAuthority(user.getUserRole());

            return buildUserForAuthentication(user, authorities);
        } catch (NoResultException nre) {
            throw new UsernameNotFoundException(nre.getMessage());
        }
    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
