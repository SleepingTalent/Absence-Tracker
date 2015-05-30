package com.noveria.absencemanagement.model.user.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */
@Repository
public class UserDAO extends BaseDAO<User>{

    public User create(User user) {
        user = super.create(user);


        List<UserRole> persistedRoles = new ArrayList<UserRole>();

        for(UserRole userRole : user.getUserRole()) {

            userRole.setUserId(user);

            entityManager.persist(userRole);

            persistedRoles.add(userRole);
        }

        user.setUserRole(persistedRoles);

        return user;
    }

    public User findUserByUsername(String username) {
        String sql = "select u from User u where u.username = :username";

        Query query = entityManager.createQuery(sql);
        query.setParameter("username",username);

        return (User) query.getSingleResult();
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
