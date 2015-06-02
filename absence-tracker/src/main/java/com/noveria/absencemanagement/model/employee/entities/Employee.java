package com.noveria.absencemanagement.model.employee.entities;


import javax.persistence.*;
import java.util.Date;

/**
 * This class represents a single row in the
 * employee Table.
 *
 * The table contains the following columns;
 * String first name and last name. Date Date of
 * birth.
 *
 * @author lynseymcgregor
 */

@Entity
public class Employee {

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
