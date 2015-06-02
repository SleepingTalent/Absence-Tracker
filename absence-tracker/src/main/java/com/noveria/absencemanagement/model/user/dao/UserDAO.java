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
 * This is a data access class that performs operations
 * on the Users Table.
 *
 * The DAO contains methods for creating, updating, and
 * retrieving rows.
 *
 * @author lynseymcgregor
 *
 */

@Repository
public class UserDAO extends BaseDAO<User>{

    /**
     * This method wil create a new user row in the Users Table.
     * If the user has any associated user roles these will
     * also be created.
     *
     * @param user A user and roles to be persisted.
     * @return A persisted User Entity.
     */
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

    /**
     * This method searches for a User row that has a User
     * name column matching the parameter.
     *
     * @param username The username of the user we wish to find.
     * @return A persisted user entity.
     */
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
