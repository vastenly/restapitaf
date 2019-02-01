package com.vastenly.taf.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.yandex.qatools.allure.annotations.Step;

public class AllureLogger {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

    @Step("{0}")
    public static void log(String logs) {
        log.debug("Logged to allure: " + logs);
    }
}