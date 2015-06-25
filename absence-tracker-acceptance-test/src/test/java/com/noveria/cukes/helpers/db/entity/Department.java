package com.noveria.cukes.helpers.db.entity;

/**
 * Created by lynseymcgregor on 25/06/2015.
 */
public class Department {
    private String name;
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
