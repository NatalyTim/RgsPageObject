package ru.timofeeva;

import org.openqa.selenium.WebDriver;

public class Init {
    private static WebDriver driver;

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        Init.browser = browser;
    }

    private static String browser;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        Init.driver = driver;
    }


}
