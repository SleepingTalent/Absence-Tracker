package com.noveria.absencemanagement.service.user.userrole;

/**
 * Created by lynseymcgregor on 06/06/2015.
 */
public enum ApplicationRole {
    ADMIN, MANAGER, EMPLOYEE;

    public static boolean isValidRole(String name) {
        for(ApplicationRole type : ApplicationRole.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }
}
