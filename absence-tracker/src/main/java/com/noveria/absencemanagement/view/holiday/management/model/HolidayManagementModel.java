package com.noveria.absencemanagement.view.holiday.management.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementModel")
@SessionScoped
public class HolidayManagementModel {

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    HolidayAllowanceViewBean holidayAllowanceViewBean;

    private HolidayAllowanceViewBean buildHolidayAllowance() {

        Employee employee = userModel.getEmployee();

        HolidayAllowance holidayAllowance = employeeService.getHolidayAllowance(employee);

        int totalAllowance = holidayAllowance.getTotal();
        int usedAllowance = holidayAllowance.getUsed();
        int remainingAllowance = totalAllowance - usedAllowance;

        HolidayAllowanceViewBean holidayAllowanceViewBean = new HolidayAllowanceViewBean();
        holidayAllowanceViewBean.setTotal(totalAllowance);
        holidayAllowanceViewBean.setUsed(usedAllowance);
        holidayAllowanceViewBean.setRemaining(remainingAllowance);

        return holidayAllowanceViewBean;
    }

    public void refreshHolidayAllowance() {
        holidayAllowanceViewBean = buildHolidayAllowance();
    }

    public HolidayAllowanceViewBean getHolidayAllowance() {
        if(holidayAllowanceViewBean == null) {
            holidayAllowanceViewBean = buildHolidayAllowance();
        }

        return holidayAllowanceViewBean;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
