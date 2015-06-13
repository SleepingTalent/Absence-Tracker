package com.noveria.absencemanagement.model.employee.dao;

import com.noveria.absencemanagement.model.common.dao.PagenatedResults;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */
public class BrowseEmployeePagenatedResults extends PagenatedResults<EmployeeViewBean,Employee>{

    @Override
    public void setResultsList(List<Employee> results) {
        this.resultList = createViewBeanList(results);
    }


    private List<EmployeeViewBean> createViewBeanList(List<Employee> employeeList) {
        List<EmployeeViewBean> viewBeanList = new ArrayList<EmployeeViewBean>();

        for(Employee employee : employeeList) {
            EmployeeViewBean employeeViewBean = new EmployeeViewBean();
            employeeViewBean.setId(employee.getId());
            employeeViewBean.setName(employee.getFirstName()+" "+employee.getLastName());

            if(employee.getDepartment() != null) {
                employeeViewBean.setDepartment(employee.getDepartment().getDepartmentName());
            } else {
                employeeViewBean.setDepartment("");
            }

            viewBeanList.add(employeeViewBean);
        }

        return viewBeanList;

    }
}
