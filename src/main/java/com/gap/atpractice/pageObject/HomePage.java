package com.gap.atpractice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by keyhi on 5/15/2017.
 */
public class HomePage {

    //Using same driver across application
    WebDriver driver;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Returning the page title
     * @return Title
     */
    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     * Method to login with valid credentials
     * @param title page title
     * @return return true if the Login page is loaded
     */
    public Boolean isPageLoaded (String title){
        return getPageTitle().equals(title);
    }
}
