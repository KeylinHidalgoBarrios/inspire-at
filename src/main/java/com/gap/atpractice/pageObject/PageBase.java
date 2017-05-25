package com.gap.atpractice.pageObject;

import com.gap.atpractice.botStyleTest.BotStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Created by keyhi on 5/25/2017.
 */
public abstract class PageBase extends LoadableComponent {

    protected String URL_BASE = "https://vacations-management.herokuapp.com/";

    //Using same driver across application
    protected WebDriver driver;
    protected BotStyle botStyle;

    /**
     * Constructor of the page
     * @param driver Webdriver variable
     */
    public PageBase(WebDriver driver){
        this.driver = driver;
        botStyle = new BotStyle(this.driver);
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
        botStyle.waitForPageTitle(60, title);
        return getPageTitle().equals(title);
    }
}
