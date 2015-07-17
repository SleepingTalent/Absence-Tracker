package com.noveria.absencemanagement.model.holiday.annualleave.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeaveStatus;
import com.noveria.absencemanagement.service.annualleave.EmployeeAnnualLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
@Repository
public class AnnualLeaveDAO extends BaseDAO<AnnualLeave> {

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    public List<AnnualLeave> findAnnualLeaveByEmployee(Employee employee) {
        String sql = "select l from AnnualLeave l where l.employee = :employee " +
                        "and l.status != '"+ AnnualLeaveStatus.DECLINED.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee",employee);

        return query.getResultList();
    }

    public List<AnnualLeave> findDeparmentAnnualLeaveByManager(Employee manager) {
        String sql = "select l from AnnualLeave l where l.status != '"+ AnnualLeaveStatus.DECLINED.name()+"'" +
                "and l.employee in (select e from Employee e where e.department = " +
                "(select d from Department d where d.manager = :manager))";


        Query query = entityManager.createQuery(sql);
        query.setParameter("manager",manager);

        return query.getResultList();
    }

    public List<AnnualLeave> findAllAnnualLeaveByEmployee(Employee employee) {
        String sql = "select l from AnnualLeave l where l.employee = :employee";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee",employee);

        return query.getResultList();
    }

    public List<AnnualLeave> findAnnualLeaveAwaitingAuthorisationByEmployee(Employee employee) {
        String sql = "select l from AnnualLeave l where l.employee = :employee " +
                "and l.status = '"+ AnnualLeaveStatus.AWAITING_AUTHORISATION.name()+"'";

        Query query = entityManager.createQuery(sql);
        query.setParameter("employee",employee);

        return query.getResultList();
    }

    public List<EmployeeAnnualLeave> findAnnualLeaveToAuthoriseByManager(Employee manager) {
        List<Department> managersDepartments = departmentDAO.findDepartmentbyManager(manager);
        List<Employee> employees = new ArrayList<Employee>();

        for(Department department : managersDepartments) {
            List<Employee> employeesByDepartment = employeeDAO.findEmployeesbyDepartmentId(department);

            employees.addAll(employeesByDepartment);
        }

        List<EmployeeAnnualLeave> employeeAnnualLeaveList = new ArrayList<EmployeeAnnualLeave>();

        for(Employee employee : employees) {
            List<AnnualLeave> annualLeave = findAnnualLeaveAwaitingAuthorisationByEmployee(employee);

            EmployeeAnnualLeave employeeAnnualLeave = new EmployeeAnnualLeave();
            employeeAnnualLeave.setEmployee(employee);
            employeeAnnualLeave.setAnnualLeaveList(annualLeave);

            employeeAnnualLeaveList.add(employeeAnnualLeave);
        }

        return employeeAnnualLeaveList;
    }

    @Override
    protected Class<AnnualLeave> getEntityClass() {
        return AnnualLeave.class;
    }
}
