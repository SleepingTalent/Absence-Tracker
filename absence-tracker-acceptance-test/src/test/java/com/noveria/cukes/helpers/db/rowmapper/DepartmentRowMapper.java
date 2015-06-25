package com.noveria.cukes.helpers.db.rowmapper;

import com.noveria.cukes.helpers.db.entity.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lynseymcgregor on 25/06/2015.
 */
public class DepartmentRowMapper implements RowMapper{

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setId(rs.getString("id"));
        department.setName(rs.getString("department_name"));
        return department;
    }
}
