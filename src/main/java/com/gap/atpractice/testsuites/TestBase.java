package com.gap.atpractice.testsuites;

import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by keyhi on 5/25/2017.
 */
public class TestBase extends SeleniumBase {
    protected static WebDriver driver;

    /**
     * Initializing Webdriver instance so every other class extending from this one has the instance
     * @param browser Browser to be used
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    protected void initSetup(String browser){
        driver = setup(browser);
    }

    /**
     * Method to quit the browser instance
     */
    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        driver.quit();
    }
}
