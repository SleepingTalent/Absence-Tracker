package com.noveria.absencemanagement.service.absence;

import com.noveria.absencemanagement.model.absence.dao.AbsenceDAO;
import com.noveria.absencemanagement.model.absence.entity.Absence;
import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.model.absence.entity.AbsenceStatus;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeaveStatus;
import com.noveria.absencemanagement.view.absence.management.view.AbsenceViewBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
@Service
public class AbsenceService {

    @Autowired
    private AbsenceDAO absenceDAO;

    @Transactional
    public void createAbsence(Date start, Date end, Employee employee) {
        Absence absence = new Absence();
        absence.setStart(start);
        absence.setEnd(end);
        absence.setEmployee(employee);
        absence.setStatus(AbsenceStatus.AWAITING_COMFIRMATION.name());

        absenceDAO.create(absence);
    }

    @Transactional
    public void updateAbsence(Long id, String reason){
        AbsenceReason absenceReason = AbsenceReason.findByDiplayName(reason);
        absenceDAO.updateAbsenceWithReason(id,absenceReason);
    }

    public List<AbsenceData> getEmployeeAuthorisedAbsenceData(Employee employee) {
        List<Absence> absenceList = absenceDAO.findAuthorisedAbsenceForEmployee(employee);
        return absenceDAO.getAuthorisedAbsenceData(absenceList);
    }

    public List<AbsenceData> getEmployeeUnauthorisedAbsenceData(Employee employee) {
        List<Absence> absenceList = absenceDAO.findUnauthorisedAbsenceForEmployee(employee);
        return absenceDAO.getUnauthorisedAbsenceData(absenceList);
    }

    public List<AbsenceData> getAuthorisedAbsenceDataByDepartment(Employee manager) {
        List<Absence> absenceList = absenceDAO.findAuthorisedAbsenceByDepartment(manager);
        return absenceDAO.getAuthorisedAbsenceData(absenceList);
    }

    public List<AbsenceData> getUnauthorisedAbsenceDataByDepartment(Employee manager) {
        List<Absence> absenceList = absenceDAO.findUnauthorisedAbsenceByDepartment(manager);
        return absenceDAO.getUnauthorisedAbsenceData(absenceList);
    }

    public List<AbsenceViewBean> getEmployeeAbsences(Employee employee){
        List<AbsenceViewBean> absenceViewBeanList = new ArrayList<AbsenceViewBean>();

        for(Absence absence : absenceDAO.findAllAbsenceByEmployee(employee)){
            AbsenceViewBean absenceViewBean = new AbsenceViewBean();
            absenceViewBean.setId(absence.getId());
            absenceViewBean.setStart(absence.getStart());
            absenceViewBean.setEnd(absence.getEnd());
            absenceViewBean.setStatus(absence.getStatus());
            absenceViewBean.setReason(absence.getReason());
            absenceViewBean.setFullName(
                    absence.getEmployee().getFirstName()+" "+
                            absence.getEmployee().getLastName());

            absenceViewBeanList.add(absenceViewBean);
        }

        return absenceViewBeanList;
    }

    public List<AbsenceViewBean> getEmployeeAbsencesAwaitingConfirmation(Employee employee){
        List<AbsenceViewBean> absenceViewBeanList = new ArrayList<AbsenceViewBean>();

        for(Absence absence : absenceDAO.findEmployeeAbsenceByAwaitingComfirmation(employee)){
            AbsenceViewBean absenceViewBean = new AbsenceViewBean();
            absenceViewBean.setId(absence.getId());
            absenceViewBean.setStart(absence.getStart());
            absenceViewBean.setEnd(absence.getEnd());
            absenceViewBean.setStatus(absence.getStatus());
            absenceViewBean.setReason(absence.getReason());
            absenceViewBean.setFullName(
                    absence.getEmployee().getFirstName()+" "+
                            absence.getEmployee().getLastName());

            absenceViewBeanList.add(absenceViewBean);
        }

        return absenceViewBeanList;
    }

    public List<AbsenceViewBean> findAllAbsenceAwaitingConfirmationByManager(Employee manager){
        List<AbsenceViewBean> absenceViewBeanList = new ArrayList<AbsenceViewBean>();

        for(Absence absence : absenceDAO.findAllAbsenceAwaitingConfirmationByManager(manager)){
            AbsenceViewBean absenceViewBean = new AbsenceViewBean();
            absenceViewBean.setId(absence.getId());
            absenceViewBean.setStart(absence.getStart());
            absenceViewBean.setEnd(absence.getEnd());
            absenceViewBean.setStatus(absence.getStatus());
            absenceViewBean.setReason(absence.getReason());
            absenceViewBean.setFullName(
                    absence.getEmployee().getFirstName()+" "+
                            absence.getEmployee().getLastName());

            absenceViewBeanList.add(absenceViewBean);
        }

        return absenceViewBeanList;
    }
}
