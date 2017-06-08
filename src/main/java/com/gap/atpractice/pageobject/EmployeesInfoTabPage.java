package com.gap.atpractice.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/1/2017.
 */
public class EmployeesInfoTabPage extends PageBase {
    //page url
    private final String url = "employees";

    //Web elements
    @FindBy(xpath = "//h1[text()='Listing employees']") private WebElement userTabTitle;
    @FindBy(xpath = "//a[@href='/employees/new']") private WebElement newEmployeeLink;
    private String employeeOption = "//td[text()='%s']/following-sibling::td/a[text()='%s']";
    private String employeeElement = "//td[text()='%s']";

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public EmployeesInfoTabPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded(){
        botStyle.waitForElementPresent(userTabTitle, 60);
        return userTabTitle.isDisplayed();
    }

    /**
     * Go to page where new Employees are created
     * @return NewEmployeePage instance with Webdriver
     */
    public NewEmployeePage clickNewEmployeeLink(){
        this.newEmployeeLink.click();

        return PageFactory.initElements(driver, NewEmployeePage.class);
    }

    /**
     * Common methot to click an employeeOption
     * @param identification to identify which employee is needed
     * @param option which employeeOption from the row needs to be clicked
     */
    private void clickOnUserOption(String identification, String option){
        botStyle.clickElement(By.xpath(String.format(employeeOption, identification, option)));
    }

    /**
     * Go to Employee details page
     * @param identification id of the user to be deleted
     * @return NewEmployeePage instance with Webdriver
     */
    public EmployeeDetailsPage clickShowDetailsLink(String identification){
        clickOnUserOption(identification, "Show details");

        return PageFactory.initElements(driver, EmployeeDetailsPage.class);
    }

    /**
     * Delete employee
     * @param identification id of the user to be deleted
     * @return
     */
    public boolean deleteEmployee(String identification){
        clickOnUserOption(identification, "Delete");

        botStyle.clickAlertOption(true);

        return userExists(identification);
    }

    /**
     * Validate if user exists
     * @param identification id of the user to validate
     * @return true if exists, false if doesn´t
     */
    public boolean userExists(String identification){
        boolean exists = botStyle.isElementPresent(By.xpath(String.format(employeeElement, identification)));

        return exists;
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
        Assert.assertTrue(url.contains("new"), "Not on Employees Information tab: ".concat(url));
    }
}

