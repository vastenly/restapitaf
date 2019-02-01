package com.vastenly.taf;


import com.vastenly.taf.app.PetStoreAPI;
import com.vastenly.taf.system.ResultsListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.vastenly.taf.system.AllureLogger.log;

@Listeners({/*WebDriverListener.class,*/ ResultsListenerAdapter.class})
public class BaseTest {

    @BeforeSuite
    public void before() {
        log("checkAccessToTheHost");
        try {
            PetStoreAPI.checkAccessToTheHost();
        } catch (Exception e) {
            log("Failed to checkAccessToTheHost");
            log(e.getMessage());
        }
    }

    @AfterMethod(groups = "positive", alwaysRun = true)
    public void afterMethod() {

    }

}