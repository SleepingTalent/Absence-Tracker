package com.noveria.absencemanagement.model.department.dao;

import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.view.department.view.DepartmentViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a data access class that performs actions
 * on the Department Table.
 *
 * This DAO contains methods for creating, updating, and
 * retrieving rows.
 *
 * @author lynseymcgregor
 */

@Repository
public class DepartmentDAO extends BaseDAO<Department> {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentDAO.class);

    public Department findDepartmentbyName(String name) {
        String sql = "select d from Department d where d.departmentName = :name";

        Query query = entityManager.createQuery(sql);
        query.setParameter("name",name);

        return (Department) query.getSingleResult();
    }

    public BrowseDepartmentPagenatedResults findAllDepartments(int first, int pageSize) {
        BrowseDepartmentPagenatedResults results = new BrowseDepartmentPagenatedResults();

        String sql = "select d from Department d";
        Query query = entityManager.createQuery(sql);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        List<Department> foundDepartments = query.getResultList();

        logger.debug("Paginated Results Count ("+foundDepartments.size()+")");

        results.setResultList(createViewBeanList(foundDepartments));

        int totalCount = (int)countAll();

        logger.debug("Total Count ("+totalCount+")");

        results.setTotalCount(totalCount);

        return results;
    }


    private List<DepartmentViewBean> createViewBeanList(List<Department> departmentList) {
        List<DepartmentViewBean> viewBeanList = new ArrayList<DepartmentViewBean>();

        for(Department department : departmentList) {
            DepartmentViewBean departmentViewBean = new DepartmentViewBean();
            departmentViewBean.setId(department.getId());
            departmentViewBean.setName(department.getDepartmentName());

            viewBeanList.add(departmentViewBean);
        }

        return viewBeanList;

    }

    @Override
    protected Class<Department> getEntityClass() {
        return Department.class;
    }
}
