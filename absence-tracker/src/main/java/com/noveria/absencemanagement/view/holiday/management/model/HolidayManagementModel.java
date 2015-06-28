package com.noveria.absencemanagement.view.holiday.management.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.DateHelper;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementModel")
@SessionScoped
public class HolidayManagementModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HolidayManagementModel.class);

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    HolidayAllowanceViewBean holidayAllowanceViewBean;

    private ScheduleModel lazyEventModel;

    @PostConstruct
    public void init() {
        lazyEventModel = buildDataModel();
    }

    public void clearDataModel() {
        init();
    }

    private ScheduleModel buildDataModel() {
        lazyEventModel = new DefaultScheduleModel();
        lazyEventModel.addEvent(new DefaultScheduleEvent("Champions League Match", DateHelper.previousDay8Pm(), DateHelper.previousDay11Pm()));
        lazyEventModel.addEvent(new DefaultScheduleEvent("Birthday Party", DateHelper.today1Pm(), DateHelper.today6Pm()));
        lazyEventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", DateHelper.nextDay9Am(), DateHelper.nextDay11Am()));
        lazyEventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", DateHelper.theDayAfter3Pm(), DateHelper.fourDaysLater3pm()));
        return lazyEventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

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
