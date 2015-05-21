package com.noveria.model.employee.dao;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
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
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class EmployeeDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    PersitenceHelper persitenceHelper;

    Employee employee;

    @Before
    public void setUp() {

        employee = new Employee();
        employee.setFirstName("Dave");
        employee.setLastName("Smith");
        employee.setDateOfBirth(new Date());

        employee = persitenceHelper.persistNewEmployee(employee);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void create_createsEmployee_AsExpected() {
        Employee employee = new Employee();
        employee.setFirstName("Rachel");
        employee.setLastName("Smith");
        employee.setDateOfBirth(new Date());

        Assert.assertNull("Expected Id to be Null!", employee.getId());

        employeeDAO.create(employee);

        Assert.assertNotNull("Expected Id to be populated!", employee.getId());
    }

    @Test(expected = NoResultException.class)
    public void delete_removesEmployee_AsExpected() {
        employeeDAO.delete(employee);
        employeeDAO.findById(employee.getId());
    }

    @Test
    public void countAll_returns_AsExpected() {
        Assert.assertEquals(1, employeeDAO.countAll());
    }

    @Test
    public void update_updatesEmployee_AsExpected() {
        employee.setLastName("modified");

        employeeDAO.update(employee);

        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals("modified", actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void findById_returns_AsExpected() {
        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void getEmployeeDetails_returns_AsExpected() {
        Employee actual = employeeDAO.getEmployeeDetails(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

}
