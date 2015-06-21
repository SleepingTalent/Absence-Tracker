package com.noveria.absencemanagement.view.department.model;

import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "departmentModel")
@SessionScoped
public class DepartmentModel implements Serializable {

    DepartmentViewBean department;

    public DepartmentViewBean getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentViewBean department) {
        this.department = department;
    }

}
