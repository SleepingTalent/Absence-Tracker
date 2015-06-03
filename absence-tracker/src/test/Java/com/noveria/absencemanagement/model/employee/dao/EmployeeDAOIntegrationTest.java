package com.noveria.absencemanagement.model.employee.dao;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.entities.Employee;
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
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class EmployeeDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    PersistenceHelper persistenceHelper;

    Employee employee;

    @Before
    public void setUp() {

        employee = new Employee();
        employee.setFirstName("Dave");
        employee.setLastName("Smith");

        employee = persistenceHelper.persistNewEmployee(employee);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void create_createsEmployee_AsExpected() {
        Employee employee = new Employee();
        employee.setFirstName("Rachel");
        employee.setLastName("Smith");

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
        Assert.assertEquals(4, employeeDAO.countAll());
    }

    @Test
    public void update_updatesEmployee_AsExpected() {
        employee.setLastName("modified");

        employeeDAO.update(employee);

        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals("modified", actual.getLastName());
    }

    @Test
    public void findById_returns_AsExpected() {
        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
    }

    @Test
    public void getEmployeeDetails_returns_AsExpected() {
        Employee actual = employeeDAO.getEmployeeDetails(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
    }

    @Test
    public void findByDepartment_returns_AsExpected(){
        Department department = departmentDAO.findDepartmentbyName("Software Development");
        Assert.assertNotNull(department);

        List<Employee> actual = employeeDAO.findEmployeesbyDepartmentId(department);

        Assert.assertNotNull(actual);
        Assert.assertEquals(2, actual.size());

        Assert.assertEquals("Dave", actual.get(0).getFirstName());
        Assert.assertEquals("Worker", actual.get(0).getLastName());

        Assert.assertEquals("Jane", actual.get(1).getFirstName());
        Assert.assertEquals("Worker", actual.get(1).getLastName());

    }

}
