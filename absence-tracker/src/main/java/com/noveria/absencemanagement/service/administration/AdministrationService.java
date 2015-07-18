package com.noveria.absencemanagement.service.administration;

import com.noveria.absencemanagement.model.department.dao.BrowseDepartmentPagenatedResults;
import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.dao.HolidayAllowanceDAO;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.administration.delegate.DepartmentDelegate;
import com.noveria.absencemanagement.service.administration.delegate.EmployeeDelegate;
import com.noveria.absencemanagement.service.administration.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.model.EmployeeModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdministrationService {

    private static final Logger logger = LoggerFactory.getLogger(AdministrationService.class);

    @Autowired
    DepartmentDelegate departmentDelegate;

    @Autowired
    EmployeeDelegate employeeDelegate;

    @Transactional
    public Employee createEmployee(EmployeeModel employeeModel) {
        return employeeDelegate.createEmployee(employeeModel);
    }

    @Transactional
    public void createDepartment(DepartmentViewBean department) {
        departmentDelegate.createDepartment(department);
    }

    public BrowseDepartmentPagenatedResults findAllDepartmentsPagenated(int first, int pageSize) {
        return departmentDelegate.findAllDepartmentsPagenated(first, pageSize);
    }

    public List<DepartmentViewBean> findAllDepartments() {
        return departmentDelegate.findAllDepartments();
    }

    public BrowseEmployeePagenatedResults findAllEmployees(int first, int pageSize) {
        return employeeDelegate.findAllEmployees(first, pageSize);
    }

    public Employee findEmployee(Long employeeId) throws EmployeeNotFoundException {
        return employeeDelegate.findEmployee(employeeId);
    }

    public List<EmployeeViewBean> findAllManagers() {
        return employeeDelegate.findAllManagers();
    }

    public List<Employee> findAllEmployeeByManager(Employee manager) {
        return employeeDelegate.findAllEmployeesByManager(manager);
    }


    public boolean departmentAlreadyExists(String name) {
        return departmentDelegate.departmentAlreadyExists(name);
    }
}
