package com.vastenly.taf;

import com.vastenly.taf.system.AnnotationTransformer;
import com.vastenly.taf.system.TestRetry;
import com.vastenly.taf.system.WebDriverListener;
import com.vastenly.taf.util.http.PetStoreAPI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({WebDriverListener.class, TestRetry.class, AnnotationTransformer.class})
public class BaseTest {

    @BeforeSuite
    public void before() {
        System.out.println("checkAccessToTheHost");
        try {
            PetStoreAPI.checkAccessToTheHost();
        } catch (Exception e) {
            System.out.println("Failed to checkAccessToTheHost");
            System.out.println(e.getMessage());
        }
    }

    @AfterMethod(groups = "time-setting", alwaysRun = true)
    public void afterMethod() {

    }

}