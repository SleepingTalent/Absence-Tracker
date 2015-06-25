package com.noveria.cukes.helpers.db;

import com.noveria.cukes.helpers.db.entity.Department;
import com.noveria.cukes.helpers.db.rowmapper.DepartmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lynseymcgregor on 25/06/2015.
 */
@Component
public class DBHelper {

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
}
