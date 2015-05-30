package com.noveria.model.user.dao;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.common.BaseIntegrationTest;
import com.noveria.helper.PersistenceHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class UserDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PersistenceHelper persistenceHelper;

    User user;

    @Before
    public void setUp() {

        user = new User();
        user.setEnabled(true);
        user.setUsername("adminUser");
        user.setPassword("adminPassword");
        user.setUserRole(createRoles(user,"admin","employee"));

        user = persistenceHelper.persistNewUser(user);
    }

    private List<UserRole> createRoles(User user, String... roles) {
        List<UserRole> userRoles = new ArrayList<UserRole>();

        for(String role : roles) {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(user);

            userRoles.add(userRole);
        }

        return userRoles;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void count_returnsAsExpected() {
        assertEquals(4, userDAO.countAll());
    }

    @Test
    public void findById_returns_AsExpected() {
        User actual = userDAO.findById(user.getId());

        Assert.assertEquals("adminUser", actual.getUsername());
        Assert.assertEquals("adminPassword", actual.getPassword());
        Assert.assertTrue("Expected Enabled to be TRUE!", actual.isEnabled());
        Assert.assertEquals(2, actual.getUserRole().size());

        Assert.assertEquals("admin", actual.getUserRole().get(0).getRole());
        Assert.assertNotNull("User Role has not been persisted", actual.getUserRole().get(0).getId().intValue());
        Assert.assertEquals("adminUser", actual.getUserRole().get(0).getUserId().getUsername());

        Assert.assertEquals("employee", actual.getUserRole().get(1).getRole());
        Assert.assertNotNull("User Role has not been persisted", actual.getUserRole().get(1).getId().intValue());
        Assert.assertEquals("adminUser", actual.getUserRole().get(1).getUserId().getUsername());
    }

    @Test
    public void findUsernameAndPassword_returns_AsExpected() {
        User actual = userDAO.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        Assert.assertEquals("adminUser", actual.getUsername());
        Assert.assertEquals("adminPassword", actual.getPassword());
        Assert.assertTrue("Expected Enabled to be TRUE!", actual.isEnabled());
        Assert.assertEquals(2, actual.getUserRole().size());

        Assert.assertEquals("admin", actual.getUserRole().get(0).getRole());
        Assert.assertNotNull("User Role has not been persisted!", actual.getUserRole().get(0).getId());
        Assert.assertEquals("adminUser", actual.getUserRole().get(0).getUserId().getUsername());

        Assert.assertEquals("employee", actual.getUserRole().get(1).getRole());
        Assert.assertNotNull("User Role has not been persisted!", actual.getUserRole().get(1).getId());
        Assert.assertEquals("adminUser", actual.getUserRole().get(1).getUserId().getUsername());
    }

    @Test(expected = NoResultException.class)
    public void findUsernameAndPassword_throwsException_whenUsernameNotFound() {
        userDAO.findUserByUsernameAndPassword("notFound", user.getPassword());
    }

    @Test(expected = NoResultException.class)
    public void findUsernameAndPassword_throwsException_whenPasswordNotFound() {
        userDAO.findUserByUsernameAndPassword(user.getUsername(), "notFound");
    }

}
