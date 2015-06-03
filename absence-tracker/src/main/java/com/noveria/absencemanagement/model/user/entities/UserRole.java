package com.noveria.absencemanagement.model.user.entities;

import javax.persistence.*;

/**
 * This class represents a single row in the
 * UserRole Table which maps to the primary key
 * of the Users Table.
 *
 * This table contains two columns;
 * a foreign key which maps to the ID in Users
 * Table. And a String representing the role.
 *
 * @author lynseymcgregor
 */

@Entity
public class UserRole {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE")
    private String role;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userId;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
