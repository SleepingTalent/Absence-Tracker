package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.service.employee.exception.EmployeeNotFoundException;
import com.noveria.common.BaseUnitTest;
import com.noveria.common.groups.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
public class EmployeeServiceTest extends BaseUnitTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDAO employeeDAO;

    Employee employee;

    @Before
    public void setUp() {

        employee = new Employee();
        employee.setId(1234l);

        when(employeeDAO.getEmployeeDetails(anyLong())).thenReturn(employee);
    }

    @Test
    public void createEmployee_createsEmployeeAsExpected() {
        employeeService.createEmployee(employee);
        verify(employeeDAO, times(1)).create(eq(employee));
    }

    @Test
    public void findEmployee_whenIdExists_returnsEmployee() throws EmployeeNotFoundException {
        Employee actual = employeeService.findEmployee(employee.getId());
        assertEquals(employee.getId(),actual.getId());
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void login_whenLoginInValid_throwsException() throws EmployeeNotFoundException {
        when(employeeDAO.getEmployeeDetails(anyLong())).thenThrow(new NoResultException());

        employeeService.findEmployee(4567l);
    }
}
