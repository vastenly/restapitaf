package com.vastenly.taf.system;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class CustomChromeDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(final DesiredCapabilities capabilities) {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        Map<String, Object> perfLogPrefs = new HashMap<String, Object>();
        perfLogPrefs.put("enableNetwork", true);
        perfLogPrefs.put("enablePage", false);
        perfLogPrefs.put("enableTimeline", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);
        options.addArguments("--start-maximized");

        cap.setCapability(ChromeOptions.CAPABILITY, options);

        return new ChromeDriver(cap);
    }
}
