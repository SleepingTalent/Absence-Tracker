package com.noveria.cukes.helpers.db.entity;

import com.noveria.cukes.helpers.DepartmentType;

/**
 * Created by lynseymcgregor on 28/06/2015.
 */
public class Employee {

    String firstname = "testEmployee";
    String lastname = "testEmployee";
    String department = DepartmentType.SOFTWARE_DEVELOPMENT.getName();
    String username = "testEmployeeUser";
    String password = "testEmployeePassword";
    boolean manager = false;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
