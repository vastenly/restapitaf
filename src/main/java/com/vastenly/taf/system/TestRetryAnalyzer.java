package com.vastenly.taf.system;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
    private static final int maxRetryCount = 1;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            String message = Thread.currentThread().getName() +
                    "Error in " + result.getName() +
                    " with status " + result.getStatus() +
                    " Retrying " + retryCount + " times";
            System.out.println(message);
            return true;
        }
        return false;
    }
}