package com.noveria.absencemanagement.model.absence.entity;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
public enum AbsenceStatus {

    AWAITING_COMFIRMATION("Pending"),
    CONFIRMED("Confirmed");

    private String displayName;

    AbsenceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AbsenceStatus findByName(String name) {
        for(AbsenceStatus type : AbsenceStatus.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" AbsenceStatus not supported!");
    }
}
