package com.noveria.absencemanagement.service.user;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.absencemanagement.service.user.exception.InvalidRolesException;
import com.noveria.absencemanagement.service.user.userrole.ApplicationRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * This class performs authorisation actions against
 * users and roles.
 *
 * @author lynseymcgregor
 */

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Autowired; is a spring command which
     * automatically injects an instance of
     * the named object into the class.
     */
    @Autowired
    UserDAO userDAO;

    /**
     * This method finds all the roles associated
     * for each user and grants them those authorities
     * associated with those roles.
     *
     * If the username is not found it throws an
     * exception.
     *
     * @param username
     * @return UserDetails - including UserRoles
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userDAO.findUserByUsername(username);

            logger.debug("User found with username: "+username);

            verifyRolesValid(user);

            List<GrantedAuthority> authorities =
                    buildUserAuthority(user.getUserRole());

            return buildUserForAuthentication(user, authorities);
        } catch (NoResultException nre) {
            throw new UsernameNotFoundException(nre.getMessage());
        } catch (InvalidRolesException ire) {
            throw new UsernameNotFoundException(ire.getMessage());
        }
    }

    private void verifyRolesValid(User user) throws InvalidRolesException {
        if(user.getUserRole().isEmpty() || doesNotHaveAValidRole(user.getUserRole())) {
            throw new InvalidRolesException("Invalid User Roles");
        }
    }

    private boolean doesNotHaveAValidRole(List<UserRole> userRoles) {

        for(UserRole userRole : userRoles) {
            if(ApplicationRole.isValidRole(userRole.getRole())){
                return false;
            }
        }

        return true;
    }

    /**
     * Builds a Spring UserDetails object containing
     * granted authorities
     *
     * @param user - Logged in User
     * @param authorities - Roles assigned to the User
     * @return Spring Framework User
     */
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    /**
     * Builds a list of all granted authorities
     * based on the user roles
     * @param userRoles - Roles assigned to the User
     * @return A list of granted authorities
     */
    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (UserRole userRole : userRoles) {

            logger.debug("Adding ApplicationRole to Authority: "+userRole.getRole());
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

    public User createUser(User user) {
        return userDAO.create(user);
    }
}
