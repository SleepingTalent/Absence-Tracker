package com.noveria.absencemanagement.model.user.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */
@Repository
public class UserDAO extends BaseDAO<User>{

    @Autowired
    UserRoleDAO userRoleDAO;

    public User create(User user) {
        user = super.create(user);


        List<UserRole> persistedRoles = new ArrayList<UserRole>();

        for(UserRole userRole : user.getUserRole()) {

            userRole.setUserId(user);

            userRole = userRoleDAO.create(userRole);

            persistedRoles.add(userRole);
        }

        user.setUserRole(persistedRoles);

        return user;
    }

    public User getUserDetailsbyId(Long id) throws NoResultException {
        return findById(id);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
