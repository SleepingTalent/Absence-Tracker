package com.noveria.absencemanagement.view.holiday.management.controller;

import com.noveria.absencemanagement.view.employee.model.BrowseEmployeeModel;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementController")
@RequestScoped
public class HolidayManagementController {

    @ManagedProperty(value = "#{holidayManagementModel}")
    HolidayManagementModel holidayManagementModel;

    public HolidayAllowanceViewBean getHolidayAllowance() {
        return holidayManagementModel.getHolidayAllowance();
    }

    public HolidayManagementModel getHolidayManagementModel() {
        return holidayManagementModel;
    }

    public void setHolidayManagementModel(HolidayManagementModel holidayManagementModel) {
        this.holidayManagementModel = holidayManagementModel;
    }
}
