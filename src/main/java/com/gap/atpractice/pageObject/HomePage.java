package com.gap.atpractice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/15/2017.
 */
public class HomePage extends PageBase {

    @FindBy(xpath = "//a[@href='/users']") private WebElement usersTab;
    @FindBy(xpath = "//span[contains(text(),'Welcome')]") private WebElement welcomeMessage;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Switch to "Administrative Users" tab
     * @return AdministrativeUsersPage instance
     */
    public AdministrativeUsersPage clickAdminUsersTab(){
        this.usersTab.click();

        return (AdministrativeUsersPage)driver;
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        return welcomeMessage.isDisplayed();
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
        this.driver.get(super.URL_BASE);
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded(){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("users"), "Not on the issue entry page: "+url);
    }
}
