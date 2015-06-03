package com.noveria.absencemanagement.model.employee.entities;


import com.noveria.absencemanagement.model.department.entities.Department;

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
    @Column(name = "ID")
    protected Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    private Department department;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
