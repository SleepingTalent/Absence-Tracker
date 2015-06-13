package com.noveria.absencemanagement.service.department;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.department.dao.BrowseDepartmentPagenatedResults;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    DepartmentDAO departmentDAO;

    @Transactional
    public void saveDepartment(DepartmentViewBean department) {
        Department toPersist = new Department();
        toPersist.setDepartmentName(department.getName());

        logger.debug("Creating Department (" + toPersist.getDepartmentName() + ")");

        departmentDAO.create(toPersist);
    }

    public BrowseDepartmentPagenatedResults findAllDepartments(int first, int pageSize) {

        logger.debug("Finding Departments (start: " + first + ") (pageSize: " + pageSize + ")");
        return departmentDAO.findAllDepartments(first, pageSize);
    }

    public boolean departmentAlreadyExists(String name) {
        try {
            departmentDAO.findDepartmentbyName(name);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
