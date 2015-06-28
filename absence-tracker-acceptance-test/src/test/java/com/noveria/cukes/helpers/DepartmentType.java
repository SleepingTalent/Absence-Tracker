package com.noveria.cukes.helpers;

/**
 * Created by lynseymcgregor on 28/06/2015.
 */
public enum DepartmentType {

    SOFTWARE_DEVELOPMENT("Software Development"),
    NO_SELECTION("Select Department");

    private final String name;

    DepartmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
