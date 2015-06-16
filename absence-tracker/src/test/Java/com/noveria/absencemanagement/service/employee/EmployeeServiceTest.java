package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.employee.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.view.employee.model.EmployeeModel;
import com.noveria.common.BaseUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
public class EmployeeServiceTest extends BaseUnitTest {

    private static final java.lang.String DEPARTMENT_ID = "1234";

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    DepartmentDAO departmentDAO;

    @Mock
    UserDAO userDAO;

    @Mock
    EmployeeModel employeeModel;

    Employee employee;

    User user;

    Department department;

    @Before
    public void setUp() {

        employee = new Employee();
        employee.setId(1234l);

        user = new User();

        department = new Department();

        when(employeeDAO.getEmployeeDetails(anyLong())).thenReturn(employee);

        when(employeeModel.getEmployeeEntity()).thenReturn(employee);
        when(employeeModel.getDepartmentId()).thenReturn(DEPARTMENT_ID);
        when(employeeModel.getUserEntity()).thenReturn(user);

        when(departmentDAO.findById(new Long(DEPARTMENT_ID))).thenReturn(department);
    }

    @Test

    public void createEmployee_createsEmployeeAsExpected() {
        employeeService.createEmployee(employeeModel);

        verify(employeeDAO, times(1)).create(eq(employee));
        verify(userDAO, times(1)).create(eq(user));
        verify(departmentDAO, times(1)).findById(eq(new Long(DEPARTMENT_ID)));
    }

    @Test
    public void findEmployee_whenIdExists_returnsEmployee() throws EmployeeNotFoundException {
        Employee actual = employeeService.findEmployee(employee.getId());
        assertEquals(employee.getId(), actual.getId());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void login_whenLoginInValid_throwsException() throws EmployeeNotFoundException {
        when(employeeDAO.getEmployeeDetails(anyLong())).thenThrow(new NoResultException());

        employeeService.findEmployee(4567l);
    }
}
