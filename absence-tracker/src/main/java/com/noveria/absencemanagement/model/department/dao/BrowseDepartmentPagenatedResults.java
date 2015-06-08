package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;

import java.util.List;

/**
 * Created by lynseymcgregor on 08/06/2015.
 */

public class BrowseDepartmentPagenatedResults {
    private List<DepartmentViewBean> resultList;
    private int totalCount;

    public void setResultList(List<DepartmentViewBean> resultList) {
        this.resultList = resultList;
    }

    public List<DepartmentViewBean> getResultList() {
        return resultList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
