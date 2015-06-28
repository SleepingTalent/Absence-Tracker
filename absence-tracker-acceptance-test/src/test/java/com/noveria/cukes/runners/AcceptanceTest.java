package com.noveria.cukes.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassRelativeResourceLoader;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false, format={"pretty","html:target/report/cucumber","json:target/report/cucumber/result.json"}, glue="com.noveria.cukes",
        features="classpath:features", tags={"~@wip"}, monochrome = true)
public class AcceptanceTest {

        static {
                System.setProperty("browser", "Phantom");
                //System.setProperty("browser", "Chrome");

                String dbLocation = System.getProperty("db.url");
                if(dbLocation == null) {
                        System.err.print("Db Location not set!");
                }else {
                   System.err.print("Db Location : "+dbLocation);
                }

        }

        @BeforeClass
        public static void setUp() {
        }

}
