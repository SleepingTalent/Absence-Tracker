package com.noveria.absencemanagement.model.holiday.annualleave.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
@Repository
public class AnnualLeaveDAO extends BaseDAO<AnnualLeave> {

    public List<AnnualLeave> findAnnualLeaveByEmployee(Employee employee) {
        String sql = "select l from AnnualLeave l where l.employee = :employee";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee",employee);

        return query.getResultList();
    }

    public List<AnnualLeave> findAnnualLeaveByEmployees(List<Employee> employees) {
        String sql = "select l from AnnualLeave l where l.employee in (:employees)";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employees",employees);

        return query.getResultList();
    }

    @Override
    protected Class<AnnualLeave> getEntityClass() {
        return AnnualLeave.class;
    }
}
