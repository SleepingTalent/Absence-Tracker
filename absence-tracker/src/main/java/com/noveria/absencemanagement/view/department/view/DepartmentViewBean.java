package com.noveria.absencemanagement.view.department.view;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
public class DepartmentViewBean {

    String name;
    private Long id;

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
}
