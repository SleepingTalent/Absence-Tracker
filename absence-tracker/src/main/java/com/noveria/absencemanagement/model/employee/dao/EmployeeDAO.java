package com.noveria.absencemanagement.model.employee.dao;

import com.noveria.absencemanagement.model.common.Role;
import com.noveria.absencemanagement.model.common.dao.BaseDAO;
import com.noveria.absencemanagement.model.common.dao.PagenatedResults;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.user.entities.User;
import com.noveria.absencemanagement.model.user.entities.UserRole;
import com.noveria.absencemanagement.view.employee.view.EmployeeViewBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO extends BaseDAO<Employee> {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    public Employee getEmployeeDetails(Long employeeId) throws NoResultException {
        return findById(employeeId);
    }

    public List<Employee> findEmployeesbyDepartmentId(Department department) {
        String sql = "select e from Employee e where e.department = :department";

        Query query = entityManager.createQuery(sql);
        query.setParameter("department",department);

        return query.getResultList();
    }

    public BrowseEmployeePagenatedResults findAllEmployees(int first, int pageSize) {
        BrowseEmployeePagenatedResults results = new BrowseEmployeePagenatedResults();

        String selectSql = "select e from Employee e";
        String countSql = "select count(e) from Employee e";

        return (BrowseEmployeePagenatedResults) findPagenatedResults(first, pageSize, results, selectSql, countSql);
    }

    public List<Employee> findAllManagers() {
        List<Employee> managerList = new ArrayList<Employee>();

        String sql = "select e from Employee e";

        Query query = entityManager.createQuery(sql);

        List<Employee> allEmpoyees = query.getResultList();

        for(Employee employee : allEmpoyees) {
            if(employee.getUser() != null && !CollectionUtils.isEmpty(employee.getUser().getUserRole())) {
                for(UserRole role : employee.getUser().getUserRole()) {
                   if(role.getRole().equals(Role.MANAGER.getName())) {
                       managerList.add(employee);
                       break;
                   }
                }
            }
        }

        return managerList;
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    public Employee findEmployeesbyUser(User user) {
        String sql = "select e from Employee e where e.user = :user";

        Query query = entityManager.createQuery(sql);
        query.setParameter("user",user);

        return (Employee) query.getSingleResult();
    }
}
