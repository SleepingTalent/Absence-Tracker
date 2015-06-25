package com.noveria.absencemanagement.view.authentication.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@ManagedBean(name = "userModel")
@SessionScoped
public class UserModel implements Serializable{

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
