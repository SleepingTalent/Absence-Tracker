package com.noveria.absencemanagement.model.user.entities;

import com.noveria.absencemanagement.model.common.entities.BaseEntity;

import javax.persistence.*;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */

@Entity
public class UserRole extends BaseEntity {

    private Integer id;
    private User user;
    private String role;

    public UserRole() {

    }

    public UserRole(User user, String role){
        this.user = user;
        this.role = role;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer userRoleId) {
        this.id = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
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
