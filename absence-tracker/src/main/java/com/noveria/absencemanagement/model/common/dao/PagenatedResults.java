package com.noveria.absencemanagement.model.common.dao;

import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;

import java.util.List;

/**
 * Created by lynseymcgregor on 13/06/2015.
 */
public abstract class PagenatedResults<T,E> {

    protected List<T> resultList;
    protected int totalCount;

    public List<T> getResultList() {
        return resultList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public abstract void setResultsList(List<E> results);
}
