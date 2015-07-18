package com.noveria.absencemanagement.model.absence.entity;

/**
 * Created by lynseymcgregor on 18/07/2015.
 */
public enum AbsenceType {
    SICK("Sick Leave");

    private final String typeName;

    AbsenceType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
