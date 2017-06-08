package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/15/2017.
 */
public class HomePage extends PageBase {

    //Web elements
    @FindBy(xpath = "//a[@href='/users']") private WebElement usersTab;
    @FindBy(xpath = "//a[@href='/employees']") private WebElement employeeInfoTab;
    @FindBy(xpath = "//a[@href='/my_account']") private WebElement myAccountTab;
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
     * @return AdministrativeUsersTabPage instance
     */
    public AdministrativeUsersTabPage clickAdminUsersTab(){
        this.usersTab.click();

        return PageFactory.initElements(driver, AdministrativeUsersTabPage.class);
    }

    /**
     * Switch to "Employee Information" tab
     * @return EmployeesInfoTabPage instance
     */
    public EmployeesInfoTabPage clickEmployeeInfoTab(){
        this.employeeInfoTab.click();

        return PageFactory.initElements(driver, EmployeesInfoTabPage.class);
    }

    /**
     * Switch to "My Account" tab
     * @return MyAccountTabPage instance
     */
    public MyAccountTabPage clickMyAccountTab(){
        this.myAccountTab.click();

        return PageFactory.initElements(driver, MyAccountTabPage.class);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        botStyle.waitForElementPresent(welcomeMessage, 60);
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
        Assert.assertTrue(url.contains("users"), "Not on Home page: ".concat(url));
    }
}
