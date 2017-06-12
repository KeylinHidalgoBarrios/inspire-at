package com.gap.atpractice.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by auto on 06/04/17.
 */
public class SeleniumBase {
    private WebDriver driver;

    /**
     * Deciding WebDriver configuration according with parameter
     * @param browserName which browser, Chrome, FF, IE
     * @return configured and initialized WebDriver
     */
    public void setup(String browserName){

        switch (browserName){
            case "Chrome":
                initChrome();
                break;
            case "IE":
                initIE();
                break;
            case  "FF":
                initFireFox();
                break;
            default:
                System.out.println("Browser not supported");
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Configuring ChromeDriver
     */
    private void initChrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("-incognito");
        options.addArguments("windowTypes", "webview");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("acceptSslCerts", true);
        this.driver = new ChromeDriver(capabilities);
    }

    /**
     * Configuring InternetExplorerDriver
     */
    private void initIE(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptSslCerts", true);
        capabilities.setCapability("applicationCacheEnabled", true);

        this.driver = new InternetExplorerDriver(capabilities);
    }

    /**
     * Configuring FireforDriver
     */
    private void initFireFox(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptSslCerts", true);
        capabilities.setCapability("applicationCacheEnabled", true);

        this.driver = new FirefoxDriver(capabilities);
    }

    public WebDriver getDriver(){
        return driver;
    }
}

