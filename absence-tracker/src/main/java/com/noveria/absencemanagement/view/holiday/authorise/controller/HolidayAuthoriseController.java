package com.noveria.absencemanagement.view.holiday.authorise.controller;

import com.noveria.absencemanagement.view.holiday.authorise.model.HolidayAuthoriseModel;
import com.noveria.absencemanagement.view.holiday.management.model.HolidayManagementModel;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayAuthoriseController")
@RequestScoped
public class HolidayAuthoriseController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayAuthoriseController.class);

    @ManagedProperty(value = "#{holidayAuthoriseModel}")
    HolidayAuthoriseModel holidayAuthoriseModel;

    public ScheduleModel getScheduleLazyModel() {
        logger.debug("getScheduleLazyModel : retrieving Schedule");
        return holidayAuthoriseModel.getLazyEventModel();
    }

    public HolidayAuthoriseModel getHolidayAuthoriseModel() {
        return holidayAuthoriseModel;
    }

    public void setHolidayAuthoriseModel(HolidayAuthoriseModel holidayAuthoriseModel) {
        this.holidayAuthoriseModel = holidayAuthoriseModel;
    }

    public List<HolidayRequestViewingBean> getPendingHolidayRequests() {
        return getHolidayAuthoriseModel().getPendingHolidayRequests();
    }

    public void approveHoliday() {
        holidayAuthoriseModel.authoriseAnnualLeave();
    }

    public void declineHoliday() {
        holidayAuthoriseModel.declineAnnualLeave();
    }

    public void setSelectedRequest(HolidayRequestViewingBean selected) {
        holidayAuthoriseModel.setSelectedRequest(selected);
    }

    public HolidayRequestViewingBean getSelectedRequest() {
        return holidayAuthoriseModel.getSelectedRequest();
    }
}
