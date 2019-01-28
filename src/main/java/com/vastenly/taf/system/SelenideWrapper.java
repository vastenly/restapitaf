package com.vastenly.taf.system;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenidePageFactory;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.SelenideFieldDecorator;
import com.vastenly.taf.pages.BasePage;

import java.lang.reflect.Constructor;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SelenideWrapper extends Selenide {

    private static boolean checkFbq = false;

    public static <PageObjectClass, T extends PageObjectClass> PageObjectClass page(T pageObject) {
        SelenidePageFactory.initElements(new SelenideFieldDecorator(getWebDriver()), pageObject);
        ((BasePage) pageObject).checkPageContent();
        return pageObject;
    }

    public static <PageObjectClass> PageObjectClass page(Class<PageObjectClass> pageObjectClass) {
        try {
            Constructor<PageObjectClass> constructor = pageObjectClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return page(constructor.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create new instance of " + pageObjectClass, e);
        }
    }

    public static <PageObjectClass> PageObjectClass open(String relativeOrAbsoluteUrl,
                                                         Class<PageObjectClass> pageObjectClassClass) {
        return open(relativeOrAbsoluteUrl, "", "", "", pageObjectClassClass);
    }

    public static <PageObjectClass> PageObjectClass open(URL absoluteUrl,
                                                         Class<PageObjectClass> pageObjectClassClass) {
        return open(absoluteUrl, "", "", "", pageObjectClassClass);
    }

    public static <PageObjectClass> PageObjectClass open(String relativeOrAbsoluteUrl,
                                                         String domain, String login, String password,
                                                         Class<PageObjectClass> pageObjectClassClass) {
        String resultUrl = relativeOrAbsoluteUrl;
//        if (!resultUrl.contains("?")) {
//            resultUrl += "?ab=a";
//        }
        open(resultUrl, domain, login, password);
        return page(pageObjectClassClass);
    }

    public static <PageObjectClass> PageObjectClass open(URL absoluteUrl, String domain, String login, String password,
                                                         Class<PageObjectClass> pageObjectClassClass) {
        open(absoluteUrl, domain, login, password);
        return page(pageObjectClassClass);
    }

    public static void close() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }

    public static boolean isMobile() {
        return (System.getProperty("selenide.browser").toLowerCase().contains("android")
                || System.getProperty("selenide.browser").toLowerCase().contains("iphone"))
                || (System.getProperty("mobile") != null);
    }
}
