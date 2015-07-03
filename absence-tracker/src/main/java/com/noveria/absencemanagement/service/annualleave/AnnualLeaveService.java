package com.noveria.absencemanagement.service.annualleave;

import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.dao.HolidayAllowanceDAO;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.annualleave.dao.AnnualLeaveDAO;

import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeaveStatus;
import com.noveria.absencemanagement.model.user.entities.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnualLeaveService {

    private static final Logger logger = LoggerFactory.getLogger(AnnualLeaveService.class);

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    HolidayAllowanceDAO holidayAllowanceDAO;

    @Autowired
    AnnualLeaveDAO annualLeaveDAO;

    @Transactional
    public void createAnnualLeave() {
        AnnualLeave annualLeave = new AnnualLeave();
        annualLeave.setStatus(AnnualLeaveStatus.AWAITING_AUTHORISATION.name());

        annualLeaveDAO.create(annualLeave);
    }

    @Transactional
    public void authoriseAnnualLeave(Long id) {
        updateAnnualLeaveStatus(id,AnnualLeaveStatus.AUTHORISED);
    }

    @Transactional
    public void declineAnnualLeave(Long id) {
        updateAnnualLeaveStatus(id,AnnualLeaveStatus.DECLINED);
    }

    private void updateAnnualLeaveStatus(Long id, AnnualLeaveStatus status) {
        AnnualLeave annualLeave = annualLeaveDAO.findById(id);
        annualLeave.setStatus(status.name());
        annualLeaveDAO.update(annualLeave);
    }

    public HolidayAllowance getHolidayAllowance(Employee employee) {
        return holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);
    }

    public List<EmployeeAnnualLeave> getAnnualLeaveByDepartment(Employee employee) {
        List<EmployeeAnnualLeave> employeeAnnualLeaveList = new ArrayList<EmployeeAnnualLeave>();

        List<Employee> employeesByDepartment = employeeDAO.findEmployeesbyDepartmentId(employee.getDepartment());

        for(Employee departmentEmployee : employeesByDepartment) {
            List<AnnualLeave> annualLeave = annualLeaveDAO.findAnnualLeaveByEmployee(departmentEmployee);

            EmployeeAnnualLeave employeeAnnualLeave = new EmployeeAnnualLeave();
            employeeAnnualLeave.setEmployee(departmentEmployee);
            employeeAnnualLeave.setAnnualLeaveList(annualLeave);

            employeeAnnualLeaveList.add(employeeAnnualLeave);
        }

        return employeeAnnualLeaveList;
    }

    public Employee findEmployeeByUser(User user) {
        return employeeDAO.findEmployeesbyUser(user);
    }
}
