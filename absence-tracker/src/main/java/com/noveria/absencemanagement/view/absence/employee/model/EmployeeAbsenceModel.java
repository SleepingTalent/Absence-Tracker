package com.noveria.absencemanagement.view.absence.employee.model;

import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.service.absence.AbsenceService;
import com.noveria.absencemanagement.service.administration.exception.EmployeeNotFoundException;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import com.noveria.absencemanagement.view.authentication.model.UserModel;
import com.noveria.absencemanagement.view.helper.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
@ManagedBean(name = "employeeAbsenceModel")
@SessionScoped
public class EmployeeAbsenceModel implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAbsenceModel.class);

    @ManagedProperty(value = "#{absenceService}")
    AbsenceService absenceService;

    @ManagedProperty(value = "#{userModel}")
    UserModel userModel;

    @ManagedProperty(value = "#{messageHelper}")
    private MessageHelper messageHelper;

    private AbsenceViewBean newAbsence;
    private AbsenceViewBean selectedAbsence;

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

    public List<AbsenceViewBean> getEmployeeAbsencesAwaitingConfirmation() {
        return absenceService.getEmployeeAbsencesAwaitingConfirmation(userModel.getEmployee());
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

    public void clearAbsence() {
        newAbsence = new AbsenceViewBean();
    }

    public void setSelectedAbsence(AbsenceViewBean selectedAbsence) {
        this.selectedAbsence = selectedAbsence;
    }

    public AbsenceViewBean getSelectedAbsence() {
        return selectedAbsence;
    }

    public void confirmAbsence() {
        logger.debug("Id : "+getSelectedAbsence().getId());
        logger.debug("Reason : "+getSelectedAbsence().getReason());

        absenceService.updateAbsence(getSelectedAbsence().getId(),
                getSelectedAbsence().getReason());

        messageHelper.addInfoMessage(
                "Absence Confirmed", "Reason : "+getSelectedAbsence().getReason());
    }

    public List<AbsenceViewBean> getEmployeeAbsences() {
            return absenceService.getEmployeeAbsences(userModel.getEmployee());
    }

    public List<AbsenceData> getUnauthorisedAbsenceData() {
        return absenceService.getEmployeeUnauthorisedAbsenceData(userModel.getEmployee());
    }

    public List<AbsenceData> getAuthorisedAbsenceData() {
        return absenceService.getEmployeeAuthorisedAbsenceData(userModel.getEmployee());
    }
}
