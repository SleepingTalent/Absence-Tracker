package com.noveria.absencemanagement.service.department;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    DepartmentDAO departmentDAO;

    public void saveDepartment(DepartmentViewBean department) {
        Department toPersist = new Department();
        toPersist.setDepartmentName(department.getName());

        logger.debug("Creating Department ("+toPersist.getDepartmentName()+")");

        departmentDAO.create(toPersist);
    }
}
