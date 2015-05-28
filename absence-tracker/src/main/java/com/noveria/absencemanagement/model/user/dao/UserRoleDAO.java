package com.noveria.absencemanagement.model.user.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

/**
 * Created by lynseymcgregor on 26/05/2015.
 */
@Repository
public class UserRoleDAO extends BaseDAO<UserRole> {

    public UserRole getUserRole(Integer roleId) throws NoResultException {
        return findById(roleId);
    }

    @Override
    protected Class<UserRole> getEntityClass() {
        return UserRole.class;
    }
}
