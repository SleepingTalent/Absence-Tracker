package com.noveria.cukes.helpers;


public enum UserType {
    ADMIN, MANAGER, EMPLOYEE;

    public static UserType findByName(String name) {
        for(UserType type : UserType.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" UserType not supported!");
    }
}
