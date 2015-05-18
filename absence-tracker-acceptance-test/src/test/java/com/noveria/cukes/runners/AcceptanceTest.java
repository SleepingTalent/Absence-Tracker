package com.noveria.cukes.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false, format={"pretty","html:target/report/cucumber","json:target/report/cucumber/result.json"}, glue="com.noveria.cukes",
        features="classpath:features", tags={"~@wip"}, monochrome = true)
public class AcceptanceTest {

        static {
                //System.setProperty("browser", "Firefox");
                System.setProperty("browser", "Phantom");


        }

        @BeforeClass
        public static void setUp() {
                /*String phantomJsBinary = System.getProperty("phantomjs.binary");

                assertNotNull(phantomJsBinary);
                assertTrue(new File(phantomJsBinary).exists());*/
        }

}
