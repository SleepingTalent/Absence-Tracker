package com.noveria.absencemanagement.model.user.entities;

import javax.persistence.*;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */

@Entity
public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

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
