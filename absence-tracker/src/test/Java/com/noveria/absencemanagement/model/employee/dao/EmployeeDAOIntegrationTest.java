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

import static org.junit.Assert.assertEquals;

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
        assertEquals(5, employeeDAO.countAll());
    }

    @Test
    public void findAllEmployees_returnsAsExpected() {
        BrowseEmployeePagenatedResults results = employeeDAO.findAllEmployees(0,10);
        assertEquals(5, results.getTotalCount());
        assertEquals(5, results.getResultList().size());
    }

    @Test
    public void findAllManagers_returnsAsExpected() {
        List<Employee> results = employeeDAO.findAllManagers();
        assertEquals(1, results.size());

        assertEquals("Bob",results.get(0).getFirstName());
        assertEquals("Gaffer",results.get(0).getLastName());
        assertEquals("bgaffer",results.get(0).getUser().getUsername());

        assertEquals(1,results.get(0).getUser().getUserRole().size());
        assertEquals("MANAGER",results.get(0).getUser().getUserRole().get(0).getRole());
    }

    @Test
    public void update_updatesEmployee_AsExpected() {
        employee.setLastName("modified");

        employeeDAO.update(employee);

        Employee actual = employeeDAO.findById(employee.getId());

        assertEquals(employee.getId(), actual.getId());
        assertEquals(employee.getFirstName(), actual.getFirstName());
        assertEquals("modified", actual.getLastName());
    }

    @Test
    public void findById_returns_AsExpected() {
        Employee actual = employeeDAO.findById(employee.getId());

        assertEquals(employee.getId(), actual.getId());
        assertEquals(employee.getFirstName(), actual.getFirstName());
        assertEquals(employee.getLastName(), actual.getLastName());
    }

    @Test
    public void getEmployeeDetails_returns_AsExpected() {
        Employee actual = employeeDAO.getEmployeeDetails(employee.getId());

        assertEquals(employee.getId(), actual.getId());
        assertEquals(employee.getFirstName(), actual.getFirstName());
        assertEquals(employee.getLastName(), actual.getLastName());
    }

    @Test
    public void findByDepartment_returns_AsExpected(){
        Department department = departmentDAO.findDepartmentbyName("Software Development");
        Assert.assertNotNull(department);

        List<Employee> actual = employeeDAO.findEmployeesbyDepartmentId(department);

        Assert.assertNotNull(actual);
        assertEquals(2, actual.size());

        assertEquals("Dave", actual.get(0).getFirstName());
        assertEquals("Worker", actual.get(0).getLastName());

        assertEquals("Jane", actual.get(1).getFirstName());
        assertEquals("Worker", actual.get(1).getLastName());

    }

}
