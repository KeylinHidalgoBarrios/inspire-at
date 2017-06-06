package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/24/2017.
 */
public class ForgotPasswordPage extends PageBase{

    //Page url
    private final String url = "users/password/new";

    //Web elements
    @FindBy(xpath = "//h2[contains(text(),'Forgot your password?')]") private WebElement pageTitle;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        botStyle.waitForElementPresent(60, pageTitle);
        return pageTitle.isDisplayed();
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
        this.driver.get(super.URL_BASE.concat(this.url));
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded(){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("new"), "Not on the Forgot Password page: ".concat(url));
    }
}
