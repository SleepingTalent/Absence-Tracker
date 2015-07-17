package com.noveria.absencemanagement.view.holiday.management.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
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

        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {

                List<EmployeeAnnualLeave> annualLeaveList = annualLeaveService.getAnnualLeaveByDepartment(userModel.getEmployee());

                for (EmployeeAnnualLeave employeeAnnualLeave : annualLeaveList) {

                    Employee employee = employeeAnnualLeave.getEmployee();

                    for (AnnualLeave annualLeave : employeeAnnualLeave.getAnnualLeaveList()) {
                        String css = (annualLeave.getStatus().equals("AUTHORISED")) ? "holidayAuth" : "holidayAwaitingAuth";

                        lazyEventModel.addEvent(buildEvent(employee.getFirstName() + " " + employee.getLastName(),
                                annualLeave.getStart(), annualLeave.getEnd(), css));
                    }
                }
            }
        };

        return lazyEventModel;
    }

    private DefaultScheduleEvent buildEvent(String title, Date start, Date end, String css) {
        DefaultScheduleEvent event = new DefaultScheduleEvent(title, start, end);
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
        if (holidayAllowanceViewBean == null) {
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
        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            logger.debug("Requesting Holiday Start Date : " + format.format(getHolidayRequest().getStart()));
            logger.debug("Requesting Holiday End Date : " + format.format(getHolidayRequest().getEnd()));

            DateUtil.validateStartAndEndDates(getHolidayRequest().getStart(),
                    getHolidayRequest().getEnd());

            annualLeaveService.createAnnualLeave(getHolidayRequest().getStart(),
                    getHolidayRequest().getEnd(), userModel.getEmployee());

            int totalDays = DateUtil.getWorkingDaysBetweenTwoDates(getHolidayRequest().getStart(),
                    getHolidayRequest().getEnd());

            logger.debug("Requesting Working Days Total : " + totalDays);

            annualLeaveService.addHolidayAllowance(userModel.getEmployee(), totalDays);

            messageHelper.addInfoMessage("Holiday Requested",
                    "(" + format.format(getHolidayRequest().getStart()) + "-" +
                            format.format(getHolidayRequest().getEnd()) + ")");

            getHolidayRequest().setStart(null);
            getHolidayRequest().setEnd(null);

        } catch (InvalidDateException ide) {
            messageHelper.addErrorMessage("Invalid Date Range",ide.getMessage());
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
}
