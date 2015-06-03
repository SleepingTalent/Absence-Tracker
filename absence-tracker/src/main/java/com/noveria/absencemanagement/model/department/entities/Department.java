package com.noveria.absencemanagement.model.department.entities;

import com.noveria.absencemanagement.model.employee.entities.Employee;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lynseymcgregor on 03/06/2015.
 */
@Entity
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @OneToOne
    private Employee manager;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String name) {
        this.departmentName = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
