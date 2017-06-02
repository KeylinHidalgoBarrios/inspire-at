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
    private String url = "employees/new";
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


    public NewEmployeePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Create a new employee
     * @param firstName Employee´s first name
     * @param lastName Employee´s last name
     * @param email Employee´s email
     * @param identification Employee´s identification
     * @param leaderName Employee´s leader name
     * @param year Employee´s start working on year
     * @param month Employee´s start working on month
     * @param day Employee´s start working on day
     */
    public EmployeeDetailsPage createNewEmployee(String firstName, String lastName, String email, String identification,
                                                 String leaderName, String year, String month, String day){
        //Fill fields with info from the test
        botStyle.type(firstNameField, firstName);
        botStyle.type(lastNameField, lastName);
        botStyle.type(emailField, email);
        botStyle.type(identificationField, identification);
        botStyle.type(leaderNameField, leaderName);
        botStyle.selectListValue(yearField, year);
        botStyle.selectListValue(monthField, month);
        botStyle.selectListValue(dayField, day);

        //Click button to begin creation process
        createButton.click();

        return new EmployeeDetailsPage(driver);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        botStyle.waitForElementPresent(60, newUserTitle);
        return newUserTitle.isDisplayed();
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load() {
        this.driver.get(String.format("%s%s", super.URL_BASE, this.url));
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("employees/new"), "Not on New Employee page: "+url);
    }
}
