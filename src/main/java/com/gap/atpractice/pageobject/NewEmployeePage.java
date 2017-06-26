package com.gap.atpractice.pageobject;

import com.gap.atpractice.framework.PageBase;
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
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param id employee id
     * @param leadName employee lead name
     * @param startWorkingYear when employee started working - year
     * @param startWorkingMonth when employee started working - month
     * @param startWorkingDay when employee started working - day
     * @return EmployeeDetailsPage instance
     */
    public EmployeeDetailsPage createNewEmployee(String firstName, String lastName, String email, String id, String leadName,
                                                 String startWorkingYear, String startWorkingMonth, String startWorkingDay){

        //Fill fields with info from the test
        typeFormFields(firstName, lastName, email, id, leadName);
        selectListElementsByValue(startWorkingYear, startWorkingMonth, startWorkingDay);

        return clickSubmitButton();
    }

    /**
     * Type valus in form's fields
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param id employee id
     * @param leadName employee lead name
     */
    private void typeFormFields(String firstName, String lastName, String email, String id, String leadName){
        botStyle.type(firstNameField, firstName);
        botStyle.type(lastNameField, lastName);
        botStyle.type(emailField, email);
        botStyle.type(identificationField, id);
        botStyle.type(leaderNameField, leadName);
    }

    /**
     * Select list element by value
     * @param startWorkingYear when employee started working - year
     * @param startWorkingMonth when employee started working - month
     * @param startWorkingDay when employee started working - day
     */
    private void selectListElementsByValue(String startWorkingYear, String startWorkingMonth, String startWorkingDay){
        botStyle.selectListElementByValue(yearField, startWorkingYear);
        botStyle.selectListElementByValue(monthField, startWorkingMonth);
        botStyle.selectListElementByValue(dayField, startWorkingDay);
    }

    /**
     * Click submit button
     * @return EmployeeDetailsPage instance
     */
    private EmployeeDetailsPage clickSubmitButton(){
        botStyle.click(createButton);

        return new EmployeeDetailsPage(driver);
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
