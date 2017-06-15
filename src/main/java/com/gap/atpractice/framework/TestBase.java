package com.gap.atpractice.framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Created by keyhi on 5/25/2017.
 */
public class TestBase extends SeleniumBase {
    protected static WebDriver driver;
    protected String testLinkKey;
    protected String testLinkUrl;

    /**
     * Setting TestLink values to access them from tests classes
     * @param key TestLink key
     * @param url TestLink API URL
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkKey", "testLinkUrl"})
    public void setTestLinkAccessValues(String key, String url){
        testLinkKey = key;
        testLinkUrl = url;
    }

    /**
     * Initializing Webdriver instance so every other class extending from this one has the instance
     * @param browser Browser to be used
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    protected void initSetup(String browser){
        setup(browser);
        driver = getDriver();
    }

    /**
     * Method to quit the browser instance
     */
    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        driver.quit();
    }
}
