package com.noveria.absencemanagement.model.common;

/**
 * Created by lynseymcgregor on 14/06/2015.
 */
public enum Role {
    MANAGER("1","MANAGER"), EMPLOYEE("2","EMPLOYEE");

    private String name;
    private String id;

    Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public static Role findById(String id) {
        for(Role role : Role.values()) {
            if(role.getId().equals(id)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Id ("+id+" not recognised!");
    }
}
