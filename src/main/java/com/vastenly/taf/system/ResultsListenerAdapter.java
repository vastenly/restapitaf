package com.vastenly.taf.system;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
//import ru.yandex.qatools.allure.Allure;


public class ResultsListenerAdapter extends TestListenerAdapter {

    private AllureLifecycleListener lifecycleListener;

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        lifecycleListener = new AllureLifecycleListener();
        //Allure.LIFECYCLE.getLifecycle(lifecycleListener);
        Allure.getLifecycle ();
    }

    @Override
    public void onFinish(ITestContext context) {
        //Allure.LIFECYCLE.fire(new RemoveDuplicatesTestSuiteEvent(lifecycleListener.suiteID));
    }
}
