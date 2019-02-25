package com.vastenly.taf.pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
//import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Assert.assertTrue;

public abstract class BasePage {

    public static String URL = "";

    @Step
    public void checkUrlContains(String url) {
        assertTrue(WebDriverRunner.url().contains(url));
    }

    @Step
    public abstract void checkPageContent();

}
