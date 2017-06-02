package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/2/2017.
 */
public class EmployeeDetailsPage extends PageBase {

    @FindBy(xpath = "//p[text()='Employee was successfully created.']") private WebElement userCreatedMessage;

    public EmployeeDetailsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded (){
        botStyle.waitForElementPresent(60, userCreatedMessage);
        return userCreatedMessage.isDisplayed();
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded(){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("users"), "Not on Login page: "+url);
    }
}
