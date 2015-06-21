package com.noveria.absencemanagement.view.authentication.model;

import com.noveria.absencemanagement.model.employee.entities.Employee;
import org.springframework.stereotype.Component;

import javax.faces.bean.SessionScoped;

/**
 * Created by lynseymcgregor on 21/06/2015.
 */
@Component
@SessionScoped
public class UserModel {

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
