package com.vastenly.taf.system;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.*;
//import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.source;

public class WebDriverListener implements IInvokedMethodListener, ITestListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        int TEST_STATUS = testResult.getStatus();
        String subName = testResult.getTestClass().getName() + ".";
        String name = subName + testResult.getName();

        if (!testResult.isSuccess()) {
            if (!SelenideWrapper.isMobile()) {
                makeLogsOnFailure();
            }
            try {
                makeScreenshotOnFailure(name);
            } catch (Exception e) {
                if (testResult.getThrowable() != null) {
                    testResult.setStatus(3);
                }
            }
        }
        if (method.isTestMethod()) {
            System.out.println("Test " + name + " was finished with status " + TEST_STATUSES.values()[TEST_STATUS - 1].name() + " in "
                    + (testResult.getEndMillis() - testResult.getStartMillis()) / 1000 + " seconds");
        }
    }

    @Attachment(value = "{0}", type = "image/png")
    private byte[] makeScreenshotOnFailure(String name) throws IOException {
        File lastSelenideScreenshot = Screenshots.takeScreenShotAsFile();
        return com.google.common.io.Files.toByteArray(lastSelenideScreenshot);
    }

    @Attachment(value = "Network logs", type = "text/plain")
    private String makeNetworkLogsOnFailure() {
        String result = null;
        for (LogEntry entry : WebDriverRunner.getWebDriver().manage().logs().get(LogType.PERFORMANCE)) {
            result += entry.toString() + "\n";
        }
        return result;
    }
    @Attachment(value = "DOM dump", type = "application/xml")
    private String makeDOMDumpOnFailure() {
        return source();
    }

    @Attachment(value = "Browser logs", type = "text/plain")
    private String makeLogsOnFailure() {
        String result = null;
        for (LogEntry logEntry : WebDriverRunner.getWebDriver().manage().logs().get("browser").getAll()) {
            result += logEntry.toString() + "\n";
        }
        return result;
    }

    @Attachment(value = "Browser cookies", type = "text/plain")
    private String makeCookiesOnFailure() {
        String result = null;
        for (Cookie cookie : WebDriverRunner.getWebDriver().manage().getCookies()) {
            result += cookie.toString() + "\n";
        }
        return result;
    }


    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }


    public void onTestFailure(ITestResult iTestResult) {

    }


    public void onTestSkipped(ITestResult iTestResult) {

    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
    }


    public void onFinish(ITestContext iTestContext) {
        WebDriverRunner.closeWebDriver();
    }

    private enum TEST_STATUSES {
        /**
         * Tests statuses
         */
        PASSED, FAILED, SKIPPED
    }
}
