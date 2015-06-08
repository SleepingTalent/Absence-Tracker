package com.noveria.absencemanagement.view.department.model;

import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.springframework.stereotype.Component;

import javax.faces.bean.SessionScoped;

@Component
@SessionScoped
public class DepartmentModel {

    DepartmentViewBean department;

    public DepartmentViewBean getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentViewBean department) {
        this.department = department;
    }

}
