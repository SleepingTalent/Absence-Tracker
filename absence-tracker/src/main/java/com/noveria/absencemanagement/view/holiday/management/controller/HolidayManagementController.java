package com.noveria.absencemanagement.view.holiday.management.controller;

import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementController")
@RequestScoped
public class HolidayManagementController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayManagementController.class);

    @ManagedProperty(value = "#{holidayManagementModel}")
    HolidayManagementModel holidayManagementModel;

    public List<HolidayAllowanceViewBean> getHolidayAllowanceList() {
        List<HolidayAllowanceViewBean> holidayAllowanceViewBeanList = new ArrayList<HolidayAllowanceViewBean>();
        holidayAllowanceViewBeanList.add(holidayManagementModel.getHolidayAllowance());
        return holidayAllowanceViewBeanList;
    }

    public HolidayAllowanceViewBean getHolidayAllowance() {
        return holidayManagementModel.getHolidayAllowance();
    }

    public ScheduleModel getScheduleLazyModel() {
        return holidayManagementModel.getLazyEventModel();
    }

    public HolidayManagementModel getHolidayManagementModel() {
        return holidayManagementModel;
    }

    public void setHolidayManagementModel(HolidayManagementModel holidayManagementModel) {
        this.holidayManagementModel = holidayManagementModel;
    }

    public void requestHoliday() {
        holidayManagementModel.requestHoliday();
    }

    public List<HolidayRequestViewingBean> getRequestHistory() {
        return holidayManagementModel.getRequestHistory();
    }
}
