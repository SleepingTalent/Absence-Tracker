package com.noveria.absencemanagement.model.employee.entities;


import com.noveria.absencemanagement.model.common.entities.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

}
