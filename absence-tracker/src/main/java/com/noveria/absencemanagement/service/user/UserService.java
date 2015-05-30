package com.noveria.absencemanagement.service.user;

import com.noveria.absencemanagement.model.user.dao.UserDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.service.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

/**
 * Created by lynseymcgregor on 30/05/2015.
 */
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User login(String username, String password) throws UserNotFoundException {
        try {
            return userDAO.findUserByUsernameAndPassword(username, password);
        } catch (NoResultException nre) {
            throw new UserNotFoundException(nre);
        }
    }
}
