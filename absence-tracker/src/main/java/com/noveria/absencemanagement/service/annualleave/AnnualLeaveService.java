package com.noveria.absencemanagement.service.annualleave;

import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.absencemanagement.model.holiday.allowance.dao.HolidayAllowanceDAO;
import com.noveria.absencemanagement.model.holiday.allowance.entities.HolidayAllowance;
import com.noveria.absencemanagement.model.holiday.allowance.entities.WorkingHours;
import com.noveria.absencemanagement.model.holiday.annualleave.dao.AnnualLeaveDAO;

import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeave;
import com.noveria.absencemanagement.model.holiday.annualleave.entity.AnnualLeaveStatus;
import com.noveria.absencemanagement.model.user.entities.User;

import com.noveria.absencemanagement.util.DateUtil;
import com.noveria.absencemanagement.view.holiday.management.view.HolidayRequestViewingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnualLeaveService {

    private static final Logger logger = LoggerFactory.getLogger(AnnualLeaveService.class);

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    HolidayAllowanceDAO holidayAllowanceDAO;

    @Autowired
    AnnualLeaveDAO annualLeaveDAO;

    @Transactional
    public void createAnnualLeave(Date start, Date end, Employee employee) {
        AnnualLeave annualLeave = new AnnualLeave();
        annualLeave.setStart(start);
        annualLeave.setEnd(end);
        annualLeave.setEmployee(employee);
        annualLeave.setStatus(AnnualLeaveStatus.AWAITING_AUTHORISATION.name());

        annualLeaveDAO.create(annualLeave);
    }

    @Transactional
    public void authoriseAnnualLeave(Long id) {
        updateAnnualLeaveStatus(id,AnnualLeaveStatus.AUTHORISED);
    }

    @Transactional
    public void declineAnnualLeave(Long id) {
        AnnualLeave annualLeave = updateAnnualLeaveStatus(id,AnnualLeaveStatus.DECLINED);

        int totalDays = DateUtil.getWorkingDaysBetweenTwoDates(annualLeave.getStart(),
                annualLeave.getEnd());

        deductHolidayAllowance(annualLeave.getEmployee(),totalDays);
    }

    private AnnualLeave updateAnnualLeaveStatus(Long id, AnnualLeaveStatus status) {
        AnnualLeave annualLeave = annualLeaveDAO.findById(id);
        annualLeave.setStatus(status.name());
        annualLeaveDAO.update(annualLeave);

        return annualLeave;
    }

    public HolidayAllowance getHolidayAllowance(Employee employee) {
        return holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);
    }

    public List<EmployeeAnnualLeave> getAnnualLeaveByManagedDepartment(Employee manager) {
        List<EmployeeAnnualLeave> employeeAnnualLeaveList = new ArrayList<EmployeeAnnualLeave>();

        List<Department> managedDepartments = departmentDAO.findDepartmentbyManager(manager);

        for(Department department : managedDepartments) {

            List<Employee> employeesByDepartment = employeeDAO.findEmployeesbyDepartmentId(department);

            for (Employee departmentEmployee : employeesByDepartment) {
                List<AnnualLeave> annualLeave = annualLeaveDAO.findAnnualLeaveByEmployee(departmentEmployee);

                EmployeeAnnualLeave employeeAnnualLeave = new EmployeeAnnualLeave();
                employeeAnnualLeave.setEmployee(departmentEmployee);
                employeeAnnualLeave.setAnnualLeaveList(annualLeave);

                employeeAnnualLeaveList.add(employeeAnnualLeave);
            }
        }

        return employeeAnnualLeaveList;
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

    public List<AnnualLeave> getEmployeeAnnualLeave(Employee employee) {
       return annualLeaveDAO.findAnnualLeaveByEmployee(employee);
    }

    public List<HolidayRequestViewingBean> getPendingHolidayRequestsForManager(Employee manager) {
        List<HolidayRequestViewingBean> pendingRequests = new ArrayList<HolidayRequestViewingBean>();

        List<EmployeeAnnualLeave> employeeAnnualLeaveList = annualLeaveDAO.findAnnualLeaveToAuthoriseByManager(manager);

        for(EmployeeAnnualLeave employeeAnnualLeave : employeeAnnualLeaveList) {

            for(AnnualLeave annualLeave : employeeAnnualLeave.getAnnualLeaveList()) {
                HolidayRequestViewingBean holidayRequestViewingBean = new HolidayRequestViewingBean();
                holidayRequestViewingBean.setId(annualLeave.getId());
                holidayRequestViewingBean.setFirstName(employeeAnnualLeave.getEmployee().getFirstName());
                holidayRequestViewingBean.setLastName(employeeAnnualLeave.getEmployee().getLastName());
                holidayRequestViewingBean.setStart(annualLeave.getStart());
                holidayRequestViewingBean.setEnd(annualLeave.getEnd());

                AnnualLeaveStatus annualLeaveStatus = AnnualLeaveStatus.findByName(annualLeave.getStatus());

                holidayRequestViewingBean.setStatus(annualLeaveStatus.getDisplayName());

                pendingRequests.add(holidayRequestViewingBean);
            }
        }

        return pendingRequests;
    }

    public Employee findEmployeeByUser(User user) {
        return employeeDAO.findEmployeesbyUser(user);
    }

    public List<HolidayRequestViewingBean> getHolidayRequests(Employee employee) {
        List<HolidayRequestViewingBean> requestHistory = new ArrayList<HolidayRequestViewingBean>();

        List<AnnualLeave> annualLeaveList = annualLeaveDAO.findAllAnnualLeaveByEmployee(employee);

        for(AnnualLeave annualLeave : annualLeaveList) {
            HolidayRequestViewingBean holidayRequestViewingBean = new HolidayRequestViewingBean();
            holidayRequestViewingBean.setId(annualLeave.getId());
            holidayRequestViewingBean.setStart(annualLeave.getStart());
            holidayRequestViewingBean.setEnd(annualLeave.getEnd());

            AnnualLeaveStatus annualLeaveStatus = AnnualLeaveStatus.findByName(annualLeave.getStatus());

            holidayRequestViewingBean.setStatus(annualLeaveStatus.getDisplayName());

            requestHistory.add(holidayRequestViewingBean);
        }

        return requestHistory;
    }

    @Transactional
    public void deductHolidayAllowance(Employee employee, int totalDays) {
        HolidayAllowance holidayAllowance = holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);

        int totalHoursRequested = totalDays * WorkingHours.DEFAULT.getDailyWorkingHours();
        int usedHolidays = holidayAllowance.getUsed();

        int totalUsed = usedHolidays - totalHoursRequested;

        holidayAllowance.setUsed(totalUsed);

        holidayAllowanceDAO.update(holidayAllowance);

    }

    @Transactional
    public void addHolidayAllowance(Employee employee, int totalDays) {
        HolidayAllowance holidayAllowance = holidayAllowanceDAO.findHolidayAllowanceByEmployee(employee);

        int totalHoursRequested = totalDays * WorkingHours.DEFAULT.getDailyWorkingHours();
        int usedHolidays = holidayAllowance.getUsed();

        int totalUsed = usedHolidays + totalHoursRequested;

        holidayAllowance.setUsed(totalUsed);

        holidayAllowanceDAO.update(holidayAllowance);

    }
}
