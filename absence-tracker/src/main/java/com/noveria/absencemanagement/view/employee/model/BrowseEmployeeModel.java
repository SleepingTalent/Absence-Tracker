package com.noveria.absencemanagement.view.employee.model;

import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.service.employee.EmployeeService;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */

@ManagedBean(name = "browseEmployeeModel")
@SessionScoped
public class BrowseEmployeeModel implements Serializable {

    private List<EmployeeViewBean> lazyLoadedData;

    private LazyDataModel<EmployeeViewBean> dataModel;

    @ManagedProperty(value = "#{employeeService}")
    EmployeeService employeeService;

    @PostConstruct
    public void init() {
        lazyLoadedData = new ArrayList<EmployeeViewBean>();
        dataModel = buildDataModel();
    }

    public void clearDataModel() { init(); }

    public LazyDataModel<EmployeeViewBean> getDataModel() {return dataModel; }

    private LazyDataModel<EmployeeViewBean> buildDataModel() {
        return new LazyDataModel<EmployeeViewBean>() {

            @Override
            public EmployeeViewBean getRowData(String rowKey) {
                for (EmployeeViewBean employeeViewBean : lazyLoadedData) {
                    String id = employeeViewBean.getId().toString();

                    if (id.equals(rowKey)) {
                        return employeeViewBean;
                    }
                }

                return null;
            }

            @Override
            public Object getRowKey(EmployeeViewBean employeeViewBean) {
                return employeeViewBean.getId();
            }

            @Override
            public List<EmployeeViewBean> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                lazyLoadedData.clear();

                BrowseEmployeePagenatedResults results = employeeService.findAllEmployees(first, pageSize);

                for(EmployeeViewBean employeeViewBean : results.getResultList()) {
                    lazyLoadedData.add(employeeViewBean);
                }

                setRowCount(results.getTotalCount());

                return lazyLoadedData;
            }
        };
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
