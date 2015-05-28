package com.noveria.model.user.dao;

import com.noveria.absencemanagement.model.user.dao.UserRoleDAO;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class UserRoleDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    PersistenceHelper persistenceHelper;

    User user;

    UserRole role;

    @Before
    public void setUp() {

        user = new User();
        user.setEnabled(true);
        user.setUsername("adminUser");
        user.setPassword("adminPassword");

        user = persistenceHelper.persistNewUser(user);

        role = new UserRole();
        role.setRole("cucumberRole");
        role.setUser(user);

        role = persistenceHelper.persistNewUserRole(role);
    }


    @After
    public void tearDown() {
    }

    @Test
    public void findById_returns_AsExpected() {
        UserRole actual = userRoleDAO.findById(role.getId());

        Assert.assertEquals("cucumberRole", actual.getRole());
        Assert.assertEquals("adminUser", actual.getUser().getUsername());
    }


}
