package com.gap.atpractice.pageObject;

import com.gap.atpractice.botStyleTest.BotStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by keyhi on 5/24/2017.
 */
public class ForgotPasswordPage {

    //Using same driver across application
    WebDriver driver;
    BotStyle botStyle;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
        botStyle = new BotStyle(this.driver);
        PageFactory.initElements(this.driver, this);
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
