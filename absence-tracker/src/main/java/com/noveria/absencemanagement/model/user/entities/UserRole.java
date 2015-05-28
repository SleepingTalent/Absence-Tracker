package com.noveria.absencemanagement.model.user.entities;

import com.noveria.absencemanagement.model.common.entities.BaseEntity;

import javax.persistence.*;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */

@Entity
public class UserRole extends BaseEntity {

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

    public void setId(Long userRoleId) {
        this.id = userRoleId;
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
