package com.gap.atpractice.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

/**
 * Created by Key on 6/7/2017.
 */
public class AddRequestVacationPage extends PageBase{

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
     * @param information Vacation information
     * @return ListVacationsPage instance which is the page displays after user created the vacation
     */
    public EmployeeDetailsPage addVacationDays(String[] information){
        botStyle.selectListElementByValue(vacationTypeList, information[0]);
        botStyle.typeWithoutClearing(vacationSinceField, information[1]);
        botStyle.typeWithoutClearing(vacationUntilField, information[2]);
        botStyle.typeWithoutClearing(vacationRequestedOnField, information[3]);
        botStyle.typeWithoutClearing(vacationDaysField, information[4]);
        botStyle.typeWithoutClearing(vacationDescriptionField, information[5]);

        //Page title needs to be clicked in order to change focus to avoid submit button overlapping with calendar element
        pageTitle.click();
        //Wait until calendar is not displayed so Submit button can be clicked
        botStyle.waitForElementNotPresent(calendarElement, 3);
        submitButton.click();

        return PageFactory.initElements(driver, EmployeeDetailsPage.class);
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
