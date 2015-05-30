package com.noveria.absencemanagement.service.user.exception;

import javax.persistence.NoResultException;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
public class UserNotFoundException extends Throwable {

    public UserNotFoundException(NoResultException e) {
        super(e);
    }
}
