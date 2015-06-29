package com.noveria.cukes.helpers.db.rowmapper;

import com.noveria.cukes.helpers.db.entity.EmployeeIdResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lynseymcgregor on 25/06/2015.
 */
public class EmployeeIdRowMapper implements RowMapper{

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeIdResult employeeIdResult = new EmployeeIdResult();
        employeeIdResult.setEmployeeId(rs.getString("ID"));
        employeeIdResult.setUserId(rs.getString("user_ID"));
        return employeeIdResult;
    }
}
