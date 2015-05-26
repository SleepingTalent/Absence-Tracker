package com.noveria.model.user.dao;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.common.BaseIntegrationTest;
import com.noveria.helper.PersitenceHelper;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class UserDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PersitenceHelper persitenceHelper;

    User user;

    @Before
    public void setUp() {

        user = new User();
        user.setEnabled(true);
        user.setUsername("adminUser");
        user.setPassword("adminPassword");
        user.setUserRole(createRoles(user,"admin","employee"));

        user = persitenceHelper.persistNewUser(user);
    }

    private List<UserRole> createRoles(User user, String... roles) {
        List<UserRole> userRoles = new ArrayList<UserRole>();

        for(String role : roles) {
            userRoles.add(new UserRole(user,role));
        }

        return userRoles;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findById_returns_AsExpected() {
        User actual = userDAO.findById(user.getUsername());

        Assert.assertEquals("adminUser", actual.getUsername());
        Assert.assertEquals("adminPassword", actual.getPassword());
        Assert.assertTrue("Expected Enabled to be TRUE!", actual.isEnabled());
        Assert.assertEquals(2, actual.getUserRole().size());

        Assert.assertEquals("admin", actual.getUserRole().get(0).getRole());
        //Assert.assertEquals("wrong", actual.getUserRole().get(0).getUserRoleId());
        Assert.assertEquals("adminUser", actual.getUserRole().get(0).getUser().getUsername());

        Assert.assertEquals("employee", actual.getUserRole().get(1).getRole());
        //Assert.assertEquals("wrong", actual.getUserRole().get(1).getUserRoleId());
        Assert.assertEquals("adminUser", actual.getUserRole().get(1).getUser().getUsername());

    }

}
