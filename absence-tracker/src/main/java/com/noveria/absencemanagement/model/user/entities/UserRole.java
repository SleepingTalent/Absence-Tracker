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
    private Integer id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    private User user;

    public UserRole() {

    }

    public UserRole(User user, String role){
        this.user = user;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer userRoleId) {
        this.id = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
