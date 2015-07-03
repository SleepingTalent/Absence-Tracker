package com.noveria.absencemanagement.service.employee;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.dao.HolidayAllowanceDAO;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.dao.AnnualLeaveDAO;

import com.noveria.absencemanagement.model.user.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    HolidayAllowanceDAO holidayAllowanceDAO;

    @Autowired
    AnnualLeaveDAO annualLeaveDAO;


    public HolidayAllowance getHolidayAllowance(Employee employee) {
        return holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);
    }

    public HolidayAllowance getAnnualLeave(Employee employee) {
        return holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);
    }

    public Employee findEmployeeByUser(User user) {
        return employeeDAO.findEmployeesbyUser(user);
    }
}
