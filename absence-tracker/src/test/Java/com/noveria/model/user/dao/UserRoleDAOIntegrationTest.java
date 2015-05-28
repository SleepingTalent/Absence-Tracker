package com.noveria.model.user.dao;

import com.noveria.absencemanagement.model.user.dao.UserRoleDAO;
import com.noveria.common.BaseIntegrationTest;
import com.noveria.helper.PersitenceHelper;
import org.junit.After;
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
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class UserRoleDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    PersitenceHelper persitenceHelper;

    @Before
    public void setUp() {

    }


    @After
    public void tearDown() {
    }

    @Test
    public void count_returnsAsExpected() {
        assertEquals(3, userRoleDAO.countAll());
    }


}
