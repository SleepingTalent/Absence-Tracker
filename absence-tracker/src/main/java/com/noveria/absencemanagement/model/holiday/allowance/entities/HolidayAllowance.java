package com.noveria.absencemanagement.model.holiday.allowance.entities;

import com.noveria.absencemanagement.model.employee.entities.Employee;

import javax.persistence.*;

/**
 * Created by lynseymcgregor on 17/06/2015.
 */
@Entity
public class HolidayAllowance {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected Long id;

    @Column(name = "TOTAL")
    private int total;

    @Column(name = "USED")
    private int used;

    @OneToOne
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void initialise(Employee employee) {
        setUsed(0);
        setTotal(HolidayAllowances.DEFAULT.getTotal());
        setEmployee(employee);
    }
}