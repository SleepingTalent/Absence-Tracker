package com.noveria.service.user;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.user.UserService;
import com.noveria.absencemanagement.service.user.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDAO userDAO;

    User user;

    @Before
    public void setUp() {
        initMocks(this);

        user = new User();
        user.setId(1234l);

        when(userDAO.findUserByUsernameAndPassword(anyString(), anyString())).thenReturn(user);
    }

    @Test
    public void login_whenLoginValid_returnsUser() throws UserNotFoundException {
        User actual = userService.login("admin","password");
        assertEquals(user.getId(),actual.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void login_whenLoginInValid_throwsException() throws UserNotFoundException {
        when(userDAO.findUserByUsernameAndPassword(anyString(), anyString())).
                thenThrow(new NoResultException());

        userService.login("wrong", "notfound");
    }

}
