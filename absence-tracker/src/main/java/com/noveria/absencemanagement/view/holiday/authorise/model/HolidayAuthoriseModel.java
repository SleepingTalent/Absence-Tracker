package com.noveria.absencemanagement.view.holiday.authorise.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.service.annualleave.AnnualLeaveService;
import com.noveria.absencemanagement.service.annualleave.EmployeeAnnualLeave;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.DateHelper;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {

                List<EmployeeAnnualLeave> annualLeaveList = annualLeaveService.getAnnualLeaveByManagedDepartment(userModel.getEmployee());

                for (EmployeeAnnualLeave employeeAnnualLeave : annualLeaveList) {

                    Employee employee = employeeAnnualLeave.getEmployee();

                    for (AnnualLeave annualLeave : employeeAnnualLeave.getAnnualLeaveList()) {

                        String css = "holidayAuth";
                        String title = employee.getFirstName() + " " + employee.getLastName();

                        if(!annualLeave.getStatus().equals("AUTHORISED")) {
                            css = "holidayAwaitingAuth";
                            title = title + " (Pending)";
                        }

                        lazyEventModel.addEvent(buildEvent(title,
                                annualLeave.getStart(), annualLeave.getEnd(), css));
                    }
                }
            }
        };

        return lazyEventModel;
    }

    private DefaultScheduleEvent buildEvent(String title, Date start, Date end, String css) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(start);
        startDate.set(Calendar.HOUR_OF_DAY, 9);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(end);
        endDate.set(Calendar.HOUR_OF_DAY, 17);

        DefaultScheduleEvent event = new DefaultScheduleEvent(title,
                startDate.getTime(), endDate.getTime());

        event.setAllDay(true);
        event.setStyleClass(css);
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
        String startDateStr = format.format(getSelectedRequest().getStart());
        String endDateStr = format.format(getSelectedRequest().getEnd());

        annualLeaveService.authoriseAnnualLeave(getSelectedRequest().getId());

        holidayInfoMessage("Holiday Authorised", startDateStr, endDateStr);
    }

    public void declineAnnualLeave() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String startDateStr = format.format(getSelectedRequest().getStart());
        String endDateStr = format.format(getSelectedRequest().getEnd());

        annualLeaveService.declineAnnualLeave(getSelectedRequest().getId());

        holidayInfoMessage("Holiday Declined", startDateStr, endDateStr);
    }

    public void holidayInfoMessage(String title, String start, String end) {
        messageHelper.addInfoMessage(title,start+"-"+end);
    }
}