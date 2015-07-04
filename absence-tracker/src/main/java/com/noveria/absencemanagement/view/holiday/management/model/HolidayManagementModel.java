package com.noveria.absencemanagement.view.holiday.management.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.service.annualleave.AnnualLeaveService;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.DateHelper;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayManagementModel")
@SessionScoped
public class HolidayManagementModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HolidayManagementModel.class);

    @ManagedProperty(value = "#{annualLeaveService}")
    AnnualLeaveService annualLeaveService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    HolidayAllowanceViewBean holidayAllowanceViewBean;

    private ScheduleModel lazyEventModel;
    private HolidayRequestViewingBean holidayRequest;

    @PostConstruct
    public void init() {
        lazyEventModel = buildDataModel();
        holidayRequest = new HolidayRequestViewingBean();
    }

    public void clearDataModel() {
        init();
    }

    private ScheduleModel buildDataModel() {
        lazyEventModel = new DefaultScheduleModel();
        lazyEventModel.addEvent(buildEvent("Joe Worker", DateHelper.daysInPast(2), DateHelper.daysInFuture(2)));
        lazyEventModel.addEvent(buildEvent("Jane Worker", DateHelper.daysInPast(7), DateHelper.daysInFuture(1)));
        lazyEventModel.addEvent(buildEvent("Joe Worker", DateHelper.daysInPast(7), DateHelper.daysInPast(3)));
        lazyEventModel.addEvent(buildEvent("Jane Worker", DateHelper.daysInFuture(2), DateHelper.daysInFuture(3)));
        return lazyEventModel;
    }

    private DefaultScheduleEvent buildEvent(String title, Date start, Date end) {
        DefaultScheduleEvent event = new DefaultScheduleEvent(title,start,end);
        event.setAllDay(true);
        return event;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private HolidayAllowanceViewBean buildHolidayAllowance() {

        Employee employee = userModel.getEmployee();

        HolidayAllowance holidayAllowance = annualLeaveService.getHolidayAllowance(employee);

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

    public AnnualLeaveService getAnnualLeaveService() {
        return annualLeaveService;
    }

    public void setAnnualLeaveService(AnnualLeaveService annualLeaveService) {
        this.annualLeaveService = annualLeaveService;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void requestHoliday() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        System.err.println("Start Date : "+format.format(getHolidayRequest().getStart()));
        System.err.println("End Date : "+format.format(getHolidayRequest().getEnd()));
        //annualLeaveService.createAnnualLeave(getHolidayRequest());

        messageHelper.addInfoMessage("Holiday Requested",
                "From : "+format.format(getHolidayRequest().getStart()) + " " +
                "To : "+format.format(getHolidayRequest().getEnd()));

        getHolidayRequest().setStart(null);
        getHolidayRequest().setEnd(null);
    }

    public HolidayRequestViewingBean getHolidayRequest() {
        return holidayRequest;
    }

    public void setHolidayRequest(HolidayRequestViewingBean holidayRequest) {
        this.holidayRequest = holidayRequest;
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public List<HolidayRequestViewingBean> getRequestHistory() {
        List<HolidayRequestViewingBean> requestHistory = new ArrayList<HolidayRequestViewingBean>();

        HolidayRequestViewingBean authoriseHoliday = new HolidayRequestViewingBean();
        authoriseHoliday.setStart(new Date());
        authoriseHoliday.setEnd(new Date());
        authoriseHoliday.setStatus("Authorised");

        HolidayRequestViewingBean awaitingAuthorisationHoliday = new HolidayRequestViewingBean();
        awaitingAuthorisationHoliday.setStart(new Date());
        awaitingAuthorisationHoliday.setEnd(new Date());
        awaitingAuthorisationHoliday.setStatus("Awaiting Authorisation");

        requestHistory.add(authoriseHoliday);
        requestHistory.add(awaitingAuthorisationHoliday);


        for(int i = 0; i< 15; i++) {
            HolidayRequestViewingBean authoriseHolidayToo = new HolidayRequestViewingBean();
            authoriseHolidayToo.setStart(new Date());
            authoriseHolidayToo.setEnd(new Date());
            authoriseHolidayToo.setStatus("Authorised");

            requestHistory.add(authoriseHolidayToo);
        }

        return requestHistory;
    }
}
