package com.noveria.absencemanagement.view;

import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@Component
@ManagedBean
public class HelloBean implements Serializable{


    private static final long serialVersionUID = 1L;

    private String name;
    private boolean userToWelcome = false;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        userToWelcome = true;
    }

    public boolean getHasUserToWelcome() {
        return userToWelcome;
    }
}