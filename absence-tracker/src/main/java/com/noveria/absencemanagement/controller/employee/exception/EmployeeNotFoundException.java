package com.noveria.absencemanagement.controller.employee.exception;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends Throwable {

    public EmployeeNotFoundException(NoResultException e) {
        super(e);
    }
}
