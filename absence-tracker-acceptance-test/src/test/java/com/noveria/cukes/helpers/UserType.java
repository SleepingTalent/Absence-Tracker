package com.noveria.cukes.helpers;


public enum UserType {
    ADMIN("admin","password"), MANAGER("manager","password"),
    EMPLOYEE("employee","password"),NOROLE("norole","password"),
    UNKNOWNROLE("unknownrole","password");

    private String username;
    private String password;

    UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static UserType findByName(String name) {
        for(UserType type : UserType.values()) {
            if(type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new RuntimeException(name+" UserType not supported!");
    }
}
