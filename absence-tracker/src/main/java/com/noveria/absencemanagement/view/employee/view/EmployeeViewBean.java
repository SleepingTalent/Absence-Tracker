package com.noveria.absencemanagement.view.employee.view;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */
public class EmployeeViewBean {
    private Long id;
    private String name;
    private String department;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
