package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.dao.HolidayAllowanceDAO;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.employee.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.service.user.UserService;
import com.noveria.absencemanagement.view.employee.model.EmployeeModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    HolidayAllowanceDAO holidayAllowanceDAO;

    @Transactional
    public Employee createEmployee(EmployeeModel employeeModel) {
        User user = employeeModel.getUserEntity();

        user = userDAO.create(user);

        Employee employee = employeeModel.getEmployeeEntity();

        Long departmentId = new Long(employeeModel.getDepartmentId());
        Department department = departmentDAO.findById(departmentId);

        employee.setDepartment(department);
        employee.setUser(user);

        logger.debug("Creating Employee : " + employee.getFirstName() + " " + employee.getLastName());

        employee = employeeDAO.create(employee);

        HolidayAllowance holidayAllowance = new HolidayAllowance();
        holidayAllowance.initialise(employee);

        holidayAllowanceDAO.create(holidayAllowance);

        return employee;
    }

    public HolidayAllowance getHolidayAllowance(Employee employee) {
        return holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);
    }

    public BrowseEmployeePagenatedResults findAllEmployees(int first, int pageSize) {

        logger.debug("Finding Employees (starts: " + first + ") (pageSize " + pageSize + ")");
        return employeeDAO.findAllEmployees(first, pageSize);
    }

    public Employee findEmployee(Long employeeId) throws EmployeeNotFoundException {

        try {
            logger.debug("Searching for Employee By Id: " + employeeId);
            return employeeDAO.getEmployeeDetails(employeeId);
        } catch (NoResultException e) {
            logger.warn("No Employee Found By Id: " + employeeId);
            throw new EmployeeNotFoundException(e);
        }
    }

    public List<EmployeeViewBean> findAllManagers() {
        List<EmployeeViewBean> managerList = new ArrayList<EmployeeViewBean>();

        logger.debug("Search for managers...");
        List<Employee> results = employeeDAO.findAllManagers();

        logger.debug("Found " + results.size() + " managers...");

        for (Employee employee : results) {
            EmployeeViewBean employeeViewBean = new EmployeeViewBean();
            employeeViewBean.setId(employee.getId());
            employeeViewBean.setFirstname(employee.getFirstName());
            employeeViewBean.setLastname(employee.getLastName());
            managerList.add(employeeViewBean);
        }

        return managerList;
    }

    public Employee findEmployeeByUser(User user) {
        return employeeDAO.findEmployeesbyUser(user);
    }
}
