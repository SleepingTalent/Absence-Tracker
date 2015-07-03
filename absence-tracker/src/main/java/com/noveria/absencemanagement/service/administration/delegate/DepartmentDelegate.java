package com.noveria.absencemanagement.service.administration.delegate;

import com.noveria.absencemanagement.model.department.dao.BrowseDepartmentPagenatedResults;
import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 03/07/2015.
 */
@Component
public class DepartmentDelegate {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentDelegate.class);

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    public void createDepartment(DepartmentViewBean department) {
        Department departmentToPersist = new Department();
        departmentToPersist.setDepartmentName(department.getName());

        if(department.getManager() != null) {
            Employee managerToPersist = employeeDAO.getEmployeeDetails(department.getManager().getId());
            departmentToPersist.setManager(managerToPersist);
        }

        logger.debug("Creating Department (" + departmentToPersist.getDepartmentName() + ")");

        departmentDAO.create(departmentToPersist);
    }

    public BrowseDepartmentPagenatedResults findAllDepartmentsPagenated(int first, int pageSize) {

        logger.debug("Finding Departments (start: " + first + ") (pageSize: " + pageSize + ")");
        return departmentDAO.findAllDepartmentsPagenated(first, pageSize);
    }

    public List<DepartmentViewBean> findAllDepartments() {
        List<DepartmentViewBean> departmentList = new ArrayList<DepartmentViewBean>();

        logger.debug("Search for Departments...");
        List<Department> results = departmentDAO.findAllDepartments();

        logger.debug("Found "+results.size()+" managers...");

        for(Department department : results) {
            DepartmentViewBean departmentViewBean = new DepartmentViewBean();
            departmentViewBean.setId(department.getId());
            departmentViewBean.setName(department.getDepartmentName());
            departmentList.add(departmentViewBean);
        }

        return departmentList;
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
