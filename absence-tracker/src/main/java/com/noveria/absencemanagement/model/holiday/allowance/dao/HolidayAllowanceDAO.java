package com.noveria.absencemanagement.model.holiday.allowance.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
@Repository
public class HolidayAllowanceDAO extends BaseDAO<HolidayAllowance> {

    public HolidayAllowance findHolidayAllowanceByEmployee(Employee employee) {
        String sql = "select h from HolidayAllowance h where h.employee = :employee";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee",employee);

        return (HolidayAllowance) query.getSingleResult();
    }

    @Override
    protected Class<HolidayAllowance> getEntityClass() {
        return HolidayAllowance.class;
    }
}
