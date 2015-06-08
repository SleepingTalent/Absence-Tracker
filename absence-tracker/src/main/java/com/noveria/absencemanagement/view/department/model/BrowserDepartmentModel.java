package com.noveria.absencemanagement.view.department.model;

import com.noveria.absencemanagement.model.department.dao.BrowseDepartmentPagenatedResults;
import com.noveria.absencemanagement.service.department.DepartmentService;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@SessionScoped
public class BrowserDepartmentModel {

    private List<DepartmentViewBean> lazyLoadedData;

    private LazyDataModel<DepartmentViewBean> dataModel;

    @Autowired
    DepartmentService departmentService;

    @PostConstruct
    public void init() {
        lazyLoadedData = new ArrayList<DepartmentViewBean>();
        dataModel = buildDataModel();
    }

    public void clearDataModel() {
        init();
    }

    public LazyDataModel<DepartmentViewBean> getDataModel() {
        return dataModel;
    }

    private LazyDataModel<DepartmentViewBean> buildDataModel() {
        return new LazyDataModel<DepartmentViewBean>() {

            @Override
            public DepartmentViewBean getRowData(String rowKey) {
                for (DepartmentViewBean departmentViewBean : lazyLoadedData) {
                    String id = departmentViewBean.getId().toString();

                    if (id.equals(rowKey)) {
                        return departmentViewBean;
                    }
                }

                return null;
            }

            @Override
            public Object getRowKey(DepartmentViewBean departmentViewBean) {
                return departmentViewBean.getId();
            }

            @Override
            public List<DepartmentViewBean> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                lazyLoadedData.clear();

                BrowseDepartmentPagenatedResults results = departmentService.findAllDepartments(first, pageSize);

                for(DepartmentViewBean departmentViewBean : results.getResultList()) {
                    lazyLoadedData.add(departmentViewBean);
                }

                setRowCount(results.getTotalCount());

                return lazyLoadedData;
            }
        };
    }
}
