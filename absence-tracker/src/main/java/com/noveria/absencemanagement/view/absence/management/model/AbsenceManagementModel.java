package com.noveria.absencemanagement.view.absence.management.model;

import com.noveria.absencemanagement.model.absence.entity.AbsenceType;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.service.absence.AbsenceService;
import com.noveria.absencemanagement.service.administration.AdministrationService;
import com.noveria.absencemanagement.service.administration.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.util.DateUtil;
import com.noveria.absencemanagement.util.InvalidDateException;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
@ManagedBean(name = "absenceManagementModel")
@SessionScoped
public class AbsenceManagementModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(AbsenceManagementModel.class);

    @ManagedProperty(value = "#{absenceService}")
    AbsenceService absenceService;

    @ManagedProperty(value = "#{administrationService}")
    AdministrationService administrationService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    private AbsenceViewBean newAbsence;

    private Long selectedEmployeeId;

    @PostConstruct
    public void init() {
        newAbsence = new AbsenceViewBean();
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public List<EmployeeViewBean> getManagedEmployees() {
        List<EmployeeViewBean> employeeViewBeanList = new ArrayList<EmployeeViewBean>();
        List<Employee> employeeList = administrationService.findAllEmployeeByManager(userModel.getEmployee());

        for (Employee employee : employeeList) {
            EmployeeViewBean employeeViewBean = new EmployeeViewBean();
            employeeViewBean.setId(employee.getId());
            employeeViewBean.setFirstname(employee.getFirstName());
            employeeViewBean.setLastname(employee.getLastName());

            employeeViewBeanList.add(employeeViewBean);
        }

        return employeeViewBeanList;
    }

    public void createAbsence() {
        try {

            Date absenceStart = getNewAbsence().getStart();
            Date absenceEnd = getNewAbsence().getEnd();

            DateUtil.validateStartAndEndDatesForAbsence(absenceStart, absenceEnd);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String absenceStartStr = format.format(absenceStart);
            String absenceEndStr = format.format(absenceEnd);

            logger.debug("Adding Absence Start Date : " + absenceStartStr);
            logger.debug("Adding Absence End Date : " + absenceEndStr);

            Employee employee = administrationService.findEmployee(new Long(getNewAbsence().getEmployeeId()));

            absenceService.createAbsence(absenceStart, absenceEnd, employee, AbsenceType.SICK.name());

            messageHelper.addInfoMessage("Absence Submitted", absenceStartStr + "-" + absenceEndStr);

            getNewAbsence().setStart(null);
            getNewAbsence().setEnd(null);
            getNewAbsence().setEmployeeId(null);

        } catch (InvalidDateException ide) {
            messageHelper.addErrorMessage("Invalid Date Range", ide.getMessage());
            throw new AbortProcessingException(ide.getMessage());
        } catch (EmployeeNotFoundException e) {
            messageHelper.addErrorMessage("Employee Not Found", "No Employee Found with Id (" + getNewAbsence().getEmployeeId() + ")");
            throw new AbortProcessingException("No Employee Found with Id (" + getNewAbsence().getEmployeeId() + ")");
        }
    }

    public List<AbsenceViewBean> findAllAbsenceAwaitingConfirmationByManager() {
        return absenceService.findAllAbsenceAwaitingConfirmationByManager(userModel.getEmployee());
    }

    public AbsenceService getAbsenceService() {
        return absenceService;
    }

    public void setAbsenceService(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public AbsenceViewBean getNewAbsence() {
        return newAbsence;
    }

    public void setNewAbsence(AbsenceViewBean newAbsence) {
        this.newAbsence = newAbsence;
    }

    public AdministrationService getAdministrationService() {
        return administrationService;
    }

    public void setAdministrationService(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    public void clearAbsence() {
        newAbsence = new AbsenceViewBean();
    }

    public void clearEmployeeAbsence() {
        selectedEmployeeId = null;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public List<AbsenceViewBean> getEmployeeAbsences() {
        try {
            Employee selectedEmployee = administrationService.findEmployee(selectedEmployeeId);
            return absenceService.getEmployeeAbsences(selectedEmployee);
        } catch (EmployeeNotFoundException e) {
            messageHelper.addErrorMessage("Employee Not Found", "No Employee Found with Id (" + selectedEmployeeId + ")");
            throw new AbortProcessingException("No Employee Found with Id (" + selectedEmployeeId + ")");
        }
    }
}
