package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class DepartmentDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Test
    public void findByDepartmentName_returns_AsExpected(){
        Department actual = departmentDAO.findDepartmentbyName("Software Development");
        Assert.assertNotNull(actual);

        Assert.assertEquals("Bob", actual.getManager().getFirstName());
        Assert.assertEquals("Gaffer", actual.getManager().getLastName());

        Assert.assertEquals(2, actual.getEmployees().size());

        Assert.assertEquals("Dave", actual.getEmployees().get(0).getFirstName());
        Assert.assertEquals("Worker", actual.getEmployees().get(0).getLastName());

        Assert.assertEquals("Jane", actual.getEmployees().get(1).getFirstName());
        Assert.assertEquals("Worker", actual.getEmployees().get(1).getLastName());
    }

    @Test
    public void findAllDepartments_returnsAsExpected() {
        BrowseDepartmentPagenatedResults results = departmentDAO.findAllDepartments(0, 10);
        assertEquals(2, results.getTotalCount());
        assertEquals(2, results.getResultList().size());
    }

}
