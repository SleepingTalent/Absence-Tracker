package com.noveria.absencemanagement.model.absence.dao;

import com.noveria.absencemanagement.model.absence.entity.Absence;
import com.noveria.absencemanagement.model.absence.entity.AbsenceData;
import com.noveria.absencemanagement.model.department.dao.DepartmentDAO;
import com.noveria.absencemanagement.model.department.entities.Department;
import com.noveria.absencemanagement.model.employee.dao.BrowseEmployeePagenatedResults;
import com.noveria.absencemanagement.model.employee.dao.EmployeeDAO;
import com.noveria.absencemanagement.model.employee.entities.Employee;
import com.noveria.common.BaseIntegrationTest;
import com.noveria.helper.PersistenceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class AbsenceDAOIntegrationTest extends BaseIntegrationTest {

    @Autowired
    AbsenceDAO absenceDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    PersistenceHelper persistenceHelper;

    Employee employee;

    @Before
    public void setUp() {
        employee = new Employee();
    }

    @Test
    public void findAuthorisedAbsenceStatsForEmployee_returnsAsExpected() {
        employee.setId(4l);
        Employee actual = employeeDAO.findById(employee.getId());

        List<Absence> absenceList = absenceDAO.findAuthorisedAbsenceForEmployee(actual);
        assertEquals(2, absenceList.size());

        List<AbsenceData> absenceDataList = absenceDAO.getAuthorisedAbsenceData(absenceList);
        assertEquals(4, absenceDataList.size());

        assertEquals("MATERNITY", absenceDataList.get(0).getAbsenceType());
        assertEquals(44, absenceDataList.get(0).getTotal());

        assertEquals("BEREAVEMENT", absenceDataList.get(1).getAbsenceType());
        assertEquals(0, absenceDataList.get(1).getTotal());

        assertEquals("CARERS", absenceDataList.get(2).getAbsenceType());
        assertEquals(2, absenceDataList.get(2).getTotal());

        assertEquals("PATERNITY", absenceDataList.get(3).getAbsenceType());
        assertEquals(0, absenceDataList.get(3).getTotal());
    }

    @Test
    public void findUnauthorisedAbsenceStatsForEmployee_returnsAsExpected() {
        employee.setId(4l);
        Employee actual = employeeDAO.findById(employee.getId());

        List<Absence> absenceList = absenceDAO.findUnauthorisedAbsenceForEmployee(actual);
        assertEquals(3, absenceList.size());

        List<AbsenceData> absenceDataList = absenceDAO.getUnauthorisedAbsenceData(absenceList);
        assertEquals(6, absenceDataList.size());

        assertEquals("COLD_FLU", absenceDataList.get(0).getAbsenceType());
        assertEquals(12, absenceDataList.get(0).getTotal());

        assertEquals("BACK_PROBLEMS", absenceDataList.get(1).getAbsenceType());
        assertEquals(0, absenceDataList.get(1).getTotal());

        assertEquals("STRESS_DEPRESSION", absenceDataList.get(2).getAbsenceType());
        assertEquals(11, absenceDataList.get(2).getTotal());

        assertEquals("DENTAL_PROBLEMS", absenceDataList.get(3).getAbsenceType());
        assertEquals(0, absenceDataList.get(3).getTotal());

        assertEquals("GI_PROBLEMS", absenceDataList.get(4).getAbsenceType());
        assertEquals(0, absenceDataList.get(4).getTotal());

        assertEquals("HEADACHE_MIGRAINE", absenceDataList.get(5).getAbsenceType());
        assertEquals(0, absenceDataList.get(5).getTotal());

    }

    @Test
    public void findAuthorisedAbsenceStatsForDepartment_returnsAsExpected() {
        employee.setId(2l);
        Employee manager = employeeDAO.findById(employee.getId());

        List<Absence> absenceList = absenceDAO.findAuthorisedAbsenceByDepartment(manager);
        assertEquals(3, absenceList.size());

        List<AbsenceData> absenceDataList = absenceDAO.getAuthorisedAbsenceData(absenceList);
        assertEquals(4, absenceDataList.size());

        assertEquals("MATERNITY", absenceDataList.get(0).getAbsenceType());
        assertEquals(44, absenceDataList.get(0).getTotal());

        assertEquals("BEREAVEMENT", absenceDataList.get(1).getAbsenceType());
        assertEquals(9, absenceDataList.get(1).getTotal());

        assertEquals("CARERS", absenceDataList.get(2).getAbsenceType());
        assertEquals(2, absenceDataList.get(2).getTotal());

        assertEquals("PATERNITY", absenceDataList.get(3).getAbsenceType());
        assertEquals(0, absenceDataList.get(3).getTotal());
    }

    @Test
    public void findUnauthorisedAbsenceStatsForDepartment_returnsAsExpected() {
        employee.setId(2l);
        Employee manager = employeeDAO.findById(employee.getId());

        List<Absence> absenceList = absenceDAO.findUnauthorisedAbsenceByDepartment(manager);
        assertEquals(5, absenceList.size());

        List<AbsenceData> absenceDataList = absenceDAO.getUnauthorisedAbsenceData(absenceList);
        assertEquals(6, absenceDataList.size());

        assertEquals("COLD_FLU", absenceDataList.get(0).getAbsenceType());
        assertEquals(14, absenceDataList.get(0).getTotal());

        assertEquals("BACK_PROBLEMS", absenceDataList.get(1).getAbsenceType());
        assertEquals(2, absenceDataList.get(1).getTotal());

        assertEquals("STRESS_DEPRESSION", absenceDataList.get(2).getAbsenceType());
        assertEquals(11, absenceDataList.get(2).getTotal());

        assertEquals("DENTAL_PROBLEMS", absenceDataList.get(3).getAbsenceType());
        assertEquals(0, absenceDataList.get(3).getTotal());

        assertEquals("GI_PROBLEMS", absenceDataList.get(4).getAbsenceType());
        assertEquals(0, absenceDataList.get(4).getTotal());

        assertEquals("HEADACHE_MIGRAINE", absenceDataList.get(5).getAbsenceType());
        assertEquals(0, absenceDataList.get(5).getTotal());

    }
}
