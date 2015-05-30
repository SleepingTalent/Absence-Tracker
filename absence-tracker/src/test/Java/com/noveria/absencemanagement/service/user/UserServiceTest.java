package com.noveria.absencemanagement.service.user;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.common.BaseUnitTest;
import com.noveria.common.groups.UnitTest;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
public class UserServiceTest extends BaseUnitTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDAO userDAO;

    User user;

    @Before
    public void setUp() {
        initMocks(this);

        List<UserRole> roleList = new ArrayList<UserRole>();

        UserRole roleOne = new UserRole();
        roleOne.setRole("roleOne");

        UserRole roleTwo = new UserRole();
        roleTwo.setRole("roleTwo");

        roleList.add(roleOne);
        roleList.add(roleTwo);

        user = new User();
        user.setId(1234l);
        user.setUsername("user");
        user.setPassword("password");
        user.setUserRole(roleList);

        when(userDAO.findUserByUsername(anyString())).thenReturn(user);
    }

    @Test
    public void login_whenLoginValid_returnsUser() {
        org.springframework.security.core.userdetails.UserDetails actual = userService.loadUserByUsername("admin");
        assertEquals(user.getUsername(),actual.getUsername());
        assertEquals(user.getPassword(),actual.getPassword());

        assertEquals(user.getUserRole().size(),actual.getAuthorities().size());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void login_whenLoginInValid_throwsException() {
        when(userDAO.findUserByUsername(anyString())).
                thenThrow(new NoResultException());

        userService.loadUserByUsername("wrong");
    }

}
