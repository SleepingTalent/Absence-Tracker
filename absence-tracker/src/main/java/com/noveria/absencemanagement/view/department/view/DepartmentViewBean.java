package com.noveria.absencemanagement.view.department.view;

import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
public class DepartmentViewBean {

    String name;
    private Long id;
    private EmployeeViewBean manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setManager(EmployeeViewBean manager) {
        this.manager = manager;
    }

    public EmployeeViewBean getManager() {
        return manager;
    }
}
