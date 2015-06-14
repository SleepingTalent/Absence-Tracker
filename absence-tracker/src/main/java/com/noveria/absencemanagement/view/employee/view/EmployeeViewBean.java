package com.noveria.absencemanagement.view.employee.view;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */
public class EmployeeViewBean {
    private Long id;
    private String firstname;
    private String lastname;
    private String department;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return firstname+" "+lastname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
