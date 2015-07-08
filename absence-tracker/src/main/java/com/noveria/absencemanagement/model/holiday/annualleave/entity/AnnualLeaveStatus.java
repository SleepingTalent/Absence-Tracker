package com.noveria.absencemanagement.model.holiday.annualleave.entity;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
public enum AnnualLeaveStatus {

    AWAITING_AUTHORISATION("Pending"),
    DECLINED("Declined"),
    AUTHORISED("Approved");

    private String displayName;

    AnnualLeaveStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AnnualLeaveStatus findByName(String name) {
        for(AnnualLeaveStatus type : AnnualLeaveStatus.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" AnnualLeaveStatus not supported!");
    }
}
