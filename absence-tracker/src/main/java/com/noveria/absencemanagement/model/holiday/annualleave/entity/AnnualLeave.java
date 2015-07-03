package com.noveria.absencemanagement.model.holiday.annualleave.entity;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowances;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
@Entity
public class AnnualLeave {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected Long id;

    @Column(name = "START")
    private Date start;

    @Column(name = "END")
    private Date end;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date total) {
        this.start = total;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date used) {
        this.end = used;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}