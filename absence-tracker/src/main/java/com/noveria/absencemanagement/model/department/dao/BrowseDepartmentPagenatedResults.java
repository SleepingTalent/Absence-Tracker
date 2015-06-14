package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.model.common.dao.PagenatedResults;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */
public class BrowseDepartmentPagenatedResults extends PagenatedResults<DepartmentViewBean,Department>{

    @Override
    public void setResultsList(List<Department> results) {
        this.resultList = createViewBeanList(results);
    }

    private List<DepartmentViewBean> createViewBeanList(List<Department> departmentList) {
        List<DepartmentViewBean> viewBeanList = new ArrayList<DepartmentViewBean>();

        for(Department department : departmentList) {
            DepartmentViewBean departmentViewBean = new DepartmentViewBean();
            departmentViewBean.setId(department.getId());
            departmentViewBean.setName(department.getDepartmentName());

            if(department.getManager() != null) {
                EmployeeViewBean manager = new EmployeeViewBean();
                manager.setId(department.getManager().getId());
                manager.setFirstname(department.getManager().getFirstName());
                manager.setLastname(department.getManager().getLastName());

                departmentViewBean.setManager(manager);
            }


            viewBeanList.add(departmentViewBean);
        }

        return viewBeanList;

    }
}
