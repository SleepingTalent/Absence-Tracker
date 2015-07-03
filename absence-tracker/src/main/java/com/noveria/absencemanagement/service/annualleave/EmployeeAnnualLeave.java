package com.noveria.absencemanagement.service.annualleave;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;

import java.util.List;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
public class EmployeeAnnualLeave {

    Employee employee;
    List<AnnualLeave> annualLeaveList;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<AnnualLeave> getAnnualLeaveList() {
        return annualLeaveList;
    }

    public void setAnnualLeaveList(List<AnnualLeave> annualLeaveList) {
        this.annualLeaveList = annualLeaveList;
    }
}
