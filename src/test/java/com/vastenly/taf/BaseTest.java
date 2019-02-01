package com.vastenly.taf;

import com.vastenly.taf.system.AnnotationTransformer;
import com.vastenly.taf.system.TestRetry;
import com.vastenly.taf.app.PetStoreAPI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import static com.vastenly.taf.util.AllureLogger.log;

@Listeners({/*WebDriverListener.class,*/ TestRetry.class, AnnotationTransformer.class})
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

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {

    }

}