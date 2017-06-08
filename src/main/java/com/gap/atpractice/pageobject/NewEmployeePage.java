package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/2/2017.
 */
public class NewEmployeePage extends PageBase {
    //Page url
    private String url = "employees/new";

    //Web employeeOption
    @FindBy(xpath = "//h1[text()='New employee']") private WebElement newUserTitle;
    @FindBy(xpath = "//input[@id='employee_first_name']") private WebElement firstNameField;
    @FindBy(xpath = "//input[@id='employee_last_name']") private WebElement lastNameField;
    @FindBy(xpath = "//input[@id='employee_email']") private WebElement emailField;
    @FindBy(xpath = "//input[@id='employee_identification']") private WebElement identificationField;
    @FindBy(xpath = "//input[@id='employee_leader_name']") private WebElement leaderNameField;
    @FindBy(xpath = "//select[@id='employee_start_working_on_1i']") private WebElement yearField;
    @FindBy(xpath = "//select[@id='employee_start_working_on_2i']") private WebElement monthField;
    @FindBy(xpath = "//select[@id='employee_start_working_on_3i']") private WebElement dayField;
    @FindBy(xpath = "//input[@name='commit']") private WebElement createButton;

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public NewEmployeePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Create a new employee
     * @param userInfo Array with user information from xml
     */
    public EmployeeDetailsPage createNewEmployee(String[] userInfo){

        //Fill fields with info from the test
        botStyle.type(firstNameField, userInfo[0]);
        botStyle.type(lastNameField, userInfo[1]);
        botStyle.type(emailField, userInfo[2]);
        botStyle.type(identificationField, userInfo[3]);
        botStyle.type(leaderNameField, userInfo[4]);
        botStyle.selectListElementByValue(yearField, userInfo[5]);
        botStyle.selectListElementByValue(monthField, userInfo[6]);
        botStyle.selectListElementByValue(dayField, userInfo[7]);

        //Click button to begin creation process
        createButton.click();

        return PageFactory.initElements(driver, EmployeeDetailsPage.class);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        botStyle.waitForElementPresent(newUserTitle, 60);
        return newUserTitle.isDisplayed();
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load() {
        this.driver.get(super.URL_BASE.concat(this.url));
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("employees/new"), "Not on New Employee page: ".concat(url));
    }
}
