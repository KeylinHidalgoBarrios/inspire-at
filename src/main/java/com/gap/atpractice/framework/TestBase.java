package com.gap.atpractice.framework;

import com.gap.atpractice.common.LoginTestCommon;
import com.gap.atpractice.framework.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by keyhi on 5/25/2017.
 */
public class TestBase extends SeleniumBase {
    protected static WebDriver driver;
    protected LoginTestCommon loginTestCommon;

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
