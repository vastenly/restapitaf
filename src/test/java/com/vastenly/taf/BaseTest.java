package com.vastenly.taf;

import com.vastenly.taf.system.AnnotationTransformer;
import com.vastenly.taf.system.TestRetry;
import com.vastenly.taf.system.WebDriverListener;
import com.vastenly.taf.util.http.PetStoreAPI;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({WebDriverListener.class, TestRetry.class, AnnotationTransformer.class})
public class BaseTest {

    private final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected JSONObject responseBody;

    @BeforeSuite
    public void before() {
        log.info("checkAccessToTheHost");
        try {
            PetStoreAPI.checkAccessToTheHost();
        } catch (Exception e) {
            log.info("Failed to checkAccessToTheHost");
            log.info(e.getMessage());
        }
    }

    @AfterMethod(groups = "time-setting", alwaysRun = true)
    public void afterMethod() {

    }

}