package com.noveria.absencemanagement.service.administration.exception;

import javax.persistence.NoResultException;

public class EmployeeNotFoundException extends Throwable {

    public EmployeeNotFoundException(NoResultException e) {
        super(e);
    }
}
