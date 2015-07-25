package com.noveria.absencemanagement.model.absence.dao;

import com.noveria.absencemanagement.model.absence.entity.Absence;
import com.noveria.absencemanagement.model.absence.entity.AbsenceStatus;
import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeaveStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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

    public Absence updateAbsenceWithReason(Long id, String reason) {
        Absence absence = findById(id);
        absence.setReason(reason);
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
}
