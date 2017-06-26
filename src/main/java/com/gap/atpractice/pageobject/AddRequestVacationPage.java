package com.gap.atpractice.pageobject;

import com.gap.atpractice.framework.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/7/2017.
 */
public class AddRequestVacationPage extends PageBase {

    @FindBy(xpath = "//h1[contains(text(),'Add/Request Vacations for:')]") private  WebElement pageTitle;
    @FindBy(id = "vacation_type") private WebElement vacationTypeList;
    @FindBy(id = "vacation_since") private  WebElement vacationSinceField;
    @FindBy(id = "vacation_until") private  WebElement vacationUntilField;
    @FindBy(id = "vacation_requested_on") private  WebElement vacationRequestedOnField;
    @FindBy(id = "vacation_days") private  WebElement vacationDaysField;
    @FindBy(id = "vacation_description") private  WebElement vacationDescriptionField;
    @FindBy(xpath = "//input[@name='commit']") private  WebElement submitButton;
    @FindBy(xpath = "//a[text()='Cancel']") private  WebElement cancelButton;
    private final By calendarElement = By.className("ui-datepicker-calendar");

    /**
     * Initialize driver and page elements with PageFactory
     * @param driver WebDriver across pages
     */
    public AddRequestVacationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Add vacation days to an employee
     * @param type type of vacations, in this case Gratification
     * @param since since value
     * @param until until values
     * @param requestedOn requested on value
     * @param totalDays total days value
     * @param description vacations description
     * @return EmployeeDetailsPage instance which is the page displays after user created the vacation
     */
    public EmployeeDetailsPage addVacationDays(String type, String since, String until, String requestedOn, String totalDays, String description){
        typeFormFields(type, since, until, requestedOn, totalDays, description);
        return clickSubmitButton();
    }

    /**
     * Deduct vacation days from an employee
     * @param type type of vacations, in this case Deduction
     * @param since since value
     * @param until until values
     * @param requestedOn requested on value
     * @param totalDays total days value
     * @param description vacations description
     * @return EmployeeDetailsPage instance which is the page displays after user created the vacation
     */
    public EmployeeDetailsPage deductVacationDays(String type, String since, String until, String requestedOn, String totalDays, String description){
        typeFormFields(type, since, until, requestedOn, totalDays, description);
        return clickSubmitButton();
    }

    /**
     * Common method to type values in fields
     * @param type type of vacations, in this case Deduction
     * @param since since value
     * @param until until values
     * @param requestedOn requested on value
     * @param totalDays total days value
     * @param description vacations description
     */
    private void typeFormFields(String type, String since, String until, String requestedOn, String totalDays, String description){
        botStyle.selectListElementByValue(vacationTypeList, type);
        botStyle.typeWithoutClearing(vacationSinceField, since);
        botStyle.typeWithoutClearing(vacationUntilField, until);
        botStyle.typeWithoutClearing(vacationRequestedOnField, requestedOn);
        botStyle.typeWithoutClearing(vacationDaysField, totalDays);
        botStyle.typeWithoutClearing(vacationDescriptionField, description);
    }

    /**
     * Remove page focus from calendar and then click submit button
     * @return EmployeeDetailsPage instance
     */
    private EmployeeDetailsPage clickSubmitButton(){
        //Page title needs to be clicked in order to change focus to avoid submit button overlapping with calendar element
        botStyle.click(pageTitle);
        //Wait until calendar is not displayed so Submit button can be clicked
        botStyle.waitForElementNotPresent(calendarElement, 3);
        botStyle.click(submitButton);

        return new EmployeeDetailsPage(driver);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded (){
        botStyle.waitForElementPresent(pageTitle, 60);
        return pageTitle.isDisplayed();
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("users"), "Not on Add/Request Vacations page: ".concat(url));
    }
}
