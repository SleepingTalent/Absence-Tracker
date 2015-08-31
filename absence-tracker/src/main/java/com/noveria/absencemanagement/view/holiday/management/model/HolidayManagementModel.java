package com.noveria.absencemanagement.view.holiday.management.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.allowance.entities.WorkingHours;
import com.noveria.absencemanagement.model.holiday.annualleave.HolidayBreakdown;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.service.annualleave.AnnualLeaveService;
import com.noveria.absencemanagement.service.annualleave.EmployeeAnnualLeave;
import com.noveria.absencemanagement.util.DateUtil;
import com.noveria.absencemanagement.util.InvalidDateException;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayAllowanceViewBean;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    private ScheduleModel lazyEventModel;
    private HolidayAllowanceViewBean holidayAllowanceViewBean;
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

        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {

                List<EmployeeAnnualLeave> annualLeaveList = annualLeaveService.getAnnualLeaveByDepartment(userModel.getEmployee());

                for (EmployeeAnnualLeave employeeAnnualLeave : annualLeaveList) {

                    Employee employee = employeeAnnualLeave.getEmployee();

                    for (AnnualLeave annualLeave : employeeAnnualLeave.getAnnualLeaveList()) {

                        String css = "holidayAuth";
                        String title = employee.getFirstName() + " " + employee.getLastName();

                        if (!annualLeave.getStatus().equals("AUTHORISED")) {
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

    private HolidayAllowanceViewBean buildHolidayAllowance() {

        Employee employee = userModel.getEmployee();

        HolidayAllowance holidayAllowance = annualLeaveService.getHolidayAllowance(employee);

        int totalAllowance = holidayAllowance.getTotal();
        int usedAllowance = holidayAllowance.getUsed();
        int remainingAllowance = holidayAllowance.getRemaining();

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
        return buildHolidayAllowance();
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
        try {

            Date holidayRequestStart = getHolidayRequest().getStart();
            Date holidayRequestEnd = getHolidayRequest().getEnd();

            DateUtil.validateStartAndEndDates(holidayRequestStart, holidayRequestEnd);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String holidayRequestStartStr = format.format(holidayRequestStart);
            String holidayRequestEndStr = format.format(holidayRequestEnd);

            logger.debug("Requesting Holiday Start Date : " + holidayRequestStartStr);
            logger.debug("Requesting Holiday End Date : " + holidayRequestEndStr);


            int totalDays = DateUtil.getWorkingDaysBetweenTwoDates(holidayRequestStart,
                    holidayRequestEnd);

            logger.debug("Requesting Working Days Total : " + totalDays);

            int totalHoursRequested = totalDays * WorkingHours.DEFAULT.getDailyWorkingHours();

            logger.debug("Requested Hours Total : " + totalHoursRequested);

            HolidayAllowance holidayAllowance = annualLeaveService.getHolidayAllowance(userModel.getEmployee());

            int remainingHolidayAllowance = holidayAllowance.getRemaining();

            logger.debug("Remaining Holiday Allowance Hours Total : " + remainingHolidayAllowance);

            if (totalHoursRequested > remainingHolidayAllowance) {
                logger.debug("Insufficient Holiday Allowance!");

                messageHelper.addErrorMessage("Holiday Request Failed",
                        "Insufficient Holiday Allowance");

                throw new AbortProcessingException("Insufficient Holiday Allowance!");
            }

            annualLeaveService.createAnnualLeave(holidayRequestStart, holidayRequestEnd,
                    userModel.getEmployee());

            annualLeaveService.addHolidayAllowance(userModel.getEmployee(), totalDays);

            messageHelper.addInfoMessage("Holiday Requested", holidayRequestStartStr + "-" + holidayRequestEndStr);

            getHolidayRequest().setStart(null);
            getHolidayRequest().setEnd(null);

        } catch (InvalidDateException ide) {
            messageHelper.addErrorMessage("Invalid Date Range", ide.getMessage());
            throw new AbortProcessingException(ide.getMessage());
        }
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
        return annualLeaveService.getHolidayRequests(userModel.getEmployee());
    }

    public void clearHolidayRequest() {
        holidayRequest = new HolidayRequestViewingBean();
    }

    public HolidayBreakdown getHolidayBreakdown() {
            List<AnnualLeave> annualLeaveList = annualLeaveService.getEmployeeAnnualLeave(userModel.getEmployee());
            HolidayBreakdown holidayBreakdown = new HolidayBreakdown();

            for (AnnualLeave annualLeave : annualLeaveList) {
                holidayBreakdown.updateHolidayBreakdown(annualLeave.getStart(), annualLeave.getEnd());
            }

            return holidayBreakdown;
    }
}
