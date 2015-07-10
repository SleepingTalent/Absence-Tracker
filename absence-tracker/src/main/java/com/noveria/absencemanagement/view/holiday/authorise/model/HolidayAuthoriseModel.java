package com.noveria.absencemanagement.view.holiday.authorise.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.service.annualleave.AnnualLeaveService;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.DateHelper;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "holidayAuthoriseModel")
@SessionScoped
public class HolidayAuthoriseModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HolidayAuthoriseModel.class);

    @ManagedProperty(value = "#{annualLeaveService}")
    AnnualLeaveService annualLeaveService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    private ScheduleModel lazyEventModel;
    private HolidayRequestViewingBean selectedRequest;

    @PostConstruct
    public void init() {
        lazyEventModel = buildDataModel();
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

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public List<HolidayRequestViewingBean> getPendingHolidayRequests() {
        return annualLeaveService.getPendingHolidayRequestsForManager(userModel.getEmployee());
    }

    public HolidayRequestViewingBean getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(HolidayRequestViewingBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public void authoriseAnnualLeave() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        annualLeaveService.authoriseAnnualLeave(getSelectedRequest().getId());

        messageHelper.addInfoMessage("Holiday Approved",
                "For :"+getSelectedRequest().getFullName()+ " " +
                "From : "+format.format(getSelectedRequest().getStart()) + " " +
                        "To : "+format.format(getSelectedRequest().getEnd()));

    }

    public void declineAnnualLeave() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        annualLeaveService.declineAnnualLeave(getSelectedRequest().getId());

        messageHelper.addInfoMessage("Holiday Declined",
                "For :"+getSelectedRequest().getFullName()+ " " +
                        "From : "+format.format(getSelectedRequest().getStart()) + " " +
                        "To : "+format.format(getSelectedRequest().getEnd()));
    }
}
