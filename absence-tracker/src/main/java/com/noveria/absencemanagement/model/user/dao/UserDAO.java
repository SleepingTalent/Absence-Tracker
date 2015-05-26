package com.noveria.absencemanagement.model.user.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */
@Repository
public class UserDAO extends BaseDAO<User>{

    public User getUserDetails(String username) throws NoResultException {
        return findById(username);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
