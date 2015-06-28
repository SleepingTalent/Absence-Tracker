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
        lazyEventModel.addEvent(buildEvent("Joe Worker", DateHelper.daysInPast(2), DateHelper.daysInFuture(2), "holidayAuth"));
        lazyEventModel.addEvent(buildEvent("Jane Worker", DateHelper.daysInPast(7), DateHelper.daysInFuture(1), "holidayAuth"));
        lazyEventModel.addEvent(buildEvent("Joe Worker", DateHelper.daysInPast(7), DateHelper.daysInPast(3), "holidayDeclined"));
        lazyEventModel.addEvent(buildEvent("Jane Worker", DateHelper.daysInFuture(2), DateHelper.daysInFuture(3), "holidayAwaitingAuth"));
        return lazyEventModel;
    }

    private DefaultScheduleEvent buildEvent(String title, Date start, Date end, String cssClass) {
        DefaultScheduleEvent event = new DefaultScheduleEvent(title,start,end,cssClass);
        event.setAllDay(true);
        return event;
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
