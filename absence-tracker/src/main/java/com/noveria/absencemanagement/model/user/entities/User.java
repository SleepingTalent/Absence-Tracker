package com.noveria.absencemanagement.model.user.entities;


import javax.persistence.*;
import java.util.List;

/**
 * This class represents a single row in the Users Table.
 *
 * The Table contains the following columns; ID, Username,
 * Password and enabled.
 *
 * The table has a one to many mapping against the UserRole
 * Table.
 *
 * @author lynseymcgregor
 *
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    protected Long id;

    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<UserRole> userRole;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public List<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }
}
