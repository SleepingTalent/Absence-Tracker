package com.noveria.cukes.helpers.db;

import com.noveria.cukes.helpers.db.entity.Department;
import com.noveria.cukes.helpers.db.entity.Employee;
import com.noveria.cukes.helpers.db.entity.EmployeeIdResult;
import com.noveria.cukes.helpers.db.rowmapper.DepartmentRowMapper;
import com.noveria.cukes.helpers.db.rowmapper.EmployeeIdRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by lynseymcgregor on 25/06/2015.
 */
@Component
public class DBHelper {

    Logger logger = LoggerFactory.getLogger(DBHelper.class);

    @Autowired
    @Qualifier("absenceTrackerJdbcTemplate")
    JdbcTemplate absenceTracker;

    public void listDepartments() {

        String sql = "select ID, DEPARTMENT_NAME from public.Department";

        List<Department> departments = absenceTracker.query(sql, new DepartmentRowMapper());

        for(Department department : departments) {
            System.err.println(department);
        }
    }

    public void deleteDepartment(String departmentName) {
        String sql = "delete from public.Department where DEPARTMENT_NAME = ?";
        absenceTracker.update(sql,departmentName);
    }

    public boolean departmentExists(String departmentName) {
        String sql = "select ID, DEPARTMENT_NAME from public.Department where DEPARTMENT_NAME = ?";

        List<Department> departments = absenceTracker.query(sql, new Object[] { departmentName },new DepartmentRowMapper());

        return !departments.isEmpty();
    }

    public void deleteEmployee(Employee employee) {

        try {
            String getUserIdSql = "select ID, user_ID from public.Employee where FIRST_NAME = ? and LAST_NAME = ?";

            EmployeeIdResult result = (EmployeeIdResult) absenceTracker.queryForObject(
                    getUserIdSql, new Object[]{employee.getFirstname(), employee.getLastname()}, new EmployeeIdRowMapper());

            String deleteAllowanceSql = "delete from public.HolidayAllowance where employee_ID = ?";
            absenceTracker.update(deleteAllowanceSql, result.getEmployeeId());

            String deleteEmployeeSql = "delete from public.Employee where ID = ?";
            absenceTracker.update(deleteEmployeeSql, result.getEmployeeId());

            String deleteUserRoleSql = "delete from public.UserRole where USER_ID = ?";
            absenceTracker.update(deleteUserRoleSql, result.getUserId());

            String deleteUserSql = "delete from public.User where id = ?";
            absenceTracker.update(deleteUserSql, result.getUserId());

        } catch (EmptyResultDataAccessException ex) {
            logger.error(ex.getMessage(),ex);
        }

    }
}
