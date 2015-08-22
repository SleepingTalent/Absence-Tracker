package com.noveria.absencemanagement.model.absence.dao;

import com.noveria.absencemanagement.model.absence.entity.Absence;
import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.absence.entity.AbsenceReason;
import com.noveria.absencemanagement.model.absence.entity.AbsenceStatus;
import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.util.DateUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
@Repository
public class AbsenceDAO extends BaseDAO<Absence> {

    @Override
    protected Class<Absence> getEntityClass() {
        return Absence.class;
    }

    public List<Absence> findAllAbsenceByEmployee(Employee employee) {
        String sql = "select a from Absence a where a.employee = :employee";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee", employee);

        return query.getResultList();
    }

    public List<Absence> findEmployeeAbsenceByAwaitingComfirmation(Employee employee) {
        String sql = "select a from Absence a where a.employee = :employee " +
                "and a.status = '"+ AbsenceStatus.AWAITING_COMFIRMATION.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee", employee);

        return query.getResultList();
    }

    public Absence updateAbsenceWithReason(Long id, AbsenceReason reason) {
        Absence absence = findById(id);
        absence.setReason(reason.name());
        absence.setStatus(AbsenceStatus.CONFIRMED.name());
        update(absence);

        return absence;
    }

    public List<Absence> findAllAbsenceByManager(Employee manager) {
        String sql = "select a from Absence a where a.employee in (select e from Employee e where e.department = " +
                "(select d from Department d where d.manager = :manager))";

        Query query = entityManager.createQuery(sql);
        query.setParameter("manager",manager);

        return query.getResultList();

    }

    public List<Absence> findAllAbsenceAwaitingConfirmationByManager(Employee manager) {
        String sql = "select a from Absence a where a.employee in (select e from Employee e where e.department = " +
                "(select d from Department d where d.manager = :manager))" +
        " and a.status = '"+ AbsenceStatus.AWAITING_COMFIRMATION.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("manager",manager);

        return query.getResultList();
    }

    public List<Absence> findAuthorisedAbsenceForEmployee(Employee employee) {
        return findConfirmedEmployeeAbsence(employee, getAuthorisedAbsencesStr());
    }

    public List<Absence> findUnauthorisedAbsenceForEmployee(Employee employee) {
        return findConfirmedEmployeeAbsence(employee, getUnauthorisedAbsencesStr());
    }

    public List<Absence> findAuthorisedAbsenceByDepartment(Employee manager) {
        return findConfirmedEmployeeAbsenceByManager(manager, getAuthorisedAbsencesStr());
    }

    public List<Absence> findUnauthorisedAbsenceByDepartment(Employee manager) {
        return findConfirmedEmployeeAbsenceByManager(manager, getUnauthorisedAbsencesStr());
    }

    private List<Absence> findConfirmedEmployeeAbsence(Employee employee, String absenceTypes) {

        String sql = "select a " +
                "from Absence a where a.employee = :employee "
                + "and a.reason in ("+absenceTypes+") "
                 + "and a.status = '"+ AbsenceStatus.CONFIRMED.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee", employee);

        return query.getResultList();
    }

    public List<Absence> findConfirmedEmployeeAbsenceByManager(Employee manager, String absenceTypes) {

        String sql = "select a " +
                "from Absence a where "
                + "a.reason in ("+absenceTypes+") "
                +" and a.employee in (select e from Employee e where e.department = " +
                                "(select d from Department d where d.manager = :manager))"
                + "and a.status = '"+ AbsenceStatus.CONFIRMED.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("manager",manager);

        return query.getResultList();
    }

    private String getAuthorisedAbsencesStr() {
        return "'"+AbsenceReason.MATERNITY.name()+"','"+AbsenceReason.BEREAVEMENT.name()
                +"','"+AbsenceReason.CARERS.name()+"','"+AbsenceReason.PATERNITY.name()+"'";
    }

    private String getUnauthorisedAbsencesStr() {
        return "'"+AbsenceReason.COLD_FLU.name()+"','"+AbsenceReason.BACK_PROBLEMS.name()
                +"','"+AbsenceReason.STRESS_DEPRESSION.name()+"','"+AbsenceReason.DENTAL_PROBLEMS.name()+"','"
                +AbsenceReason.GI_PROBLEMS.name()+"','"+AbsenceReason.HEADACHE_MIGRAINE.name()+"'";
    }

    private List<AbsenceReason> getAuthorisedAbsences() {
        List<AbsenceReason> absenceReasonList = new ArrayList<AbsenceReason>();

        absenceReasonList.add(AbsenceReason.MATERNITY);
        absenceReasonList.add(AbsenceReason.BEREAVEMENT);
        absenceReasonList.add(AbsenceReason.CARERS);
        absenceReasonList.add(AbsenceReason.PATERNITY);

        return absenceReasonList;
    }

    private List<AbsenceReason> getUnauthorisedAbsences() {
        List<AbsenceReason> absenceReasonList = new ArrayList<AbsenceReason>();

        absenceReasonList.add(AbsenceReason.COLD_FLU);
        absenceReasonList.add(AbsenceReason.BACK_PROBLEMS);
        absenceReasonList.add(AbsenceReason.STRESS_DEPRESSION);
        absenceReasonList.add(AbsenceReason.DENTAL_PROBLEMS);
        absenceReasonList.add(AbsenceReason.GI_PROBLEMS);
        absenceReasonList.add(AbsenceReason.HEADACHE_MIGRAINE);

        return absenceReasonList;
    }

    public List<AbsenceData> getAuthorisedAbsenceData(List<Absence> absenceList) {
        return getAbsenceData(absenceList,getAuthorisedAbsences());
    }

    public List<AbsenceData> getUnauthorisedAbsenceData(List<Absence> absenceList) {
        return getAbsenceData(absenceList,getUnauthorisedAbsences());
    }


    private List<AbsenceData> getAbsenceData(List<Absence> absenceList, List<AbsenceReason> absenceReasonList) {
        List<AbsenceData> absenceDataList = new ArrayList<AbsenceData>();

        for(AbsenceReason reason : absenceReasonList) {
            AbsenceData absenceData = new AbsenceData();
            absenceData.setTotal(0);
            absenceData.setAbsenceType(reason.name());

            for(Absence absence : absenceList) {
                if(absence.getReason().equals(reason.name())) {
                    int totalDays = DateUtil.getWorkingDaysBetweenTwoDates(
                            absence.getStart(),absence.getEnd());

                    absenceData.setTotal(absenceData.getTotal()+totalDays);
                }
            }

            absenceDataList.add(absenceData);
        }

        return absenceDataList;
    }
}
