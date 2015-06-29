package com.noveria.cukes.steps;

import java.io.IOException;

import com.noveria.cukes.helpers.db.DBHelper;
import com.noveria.cukes.helpers.report.ScenarioHelper;
import com.noveria.cukes.runtime.RuntimeState;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath:cucumber.xml" })
public class SetUpAndTearDownStep {

    Logger logger = LoggerFactory.getLogger(SetUpAndTearDownStep.class);

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    ScenarioHelper scenarioHelper;

    @Autowired
    DBHelper dbHelper;

    @Before
    public void setUp(Scenario scenario) {
        runtimeState.initialise();
        runtimeState.setScenario(scenario);

        //dbHelper.listDepartments();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if(runtimeState.getNewDepartmentName() != null) {
            dbHelper.deleteDepartment(runtimeState.getNewDepartmentName());
        }

        System.err.println("Employee : "+runtimeState.getEmployee());

        if(runtimeState.getEmployee() != null) {
            dbHelper.deleteEmployee(runtimeState.getEmployee());
        }

        if(scenario.isFailed()) {
            runtimeState.takeScreenShot();
        }

        runtimeState.closeBrowser();
        scenarioHelper.showDeveloperBusinessToggle(scenario);
    }
}
