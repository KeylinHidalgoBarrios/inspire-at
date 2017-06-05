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
        botStyle.waitForElementPresent(60, userTabTitle);
        return userTabTitle.isDisplayed();
    }

    /**
     * Go to page where new Employees are created
     * @return NewEmployeePage instance with Webdriver
     */
    public NewEmployeePage clickNewEmployeeLink(){
        this.newEmployeeLink.click();

        return new NewEmployeePage(driver);
    }

    /**
     * Go to Employee details page
     * @return NewEmployeePage instance with Webdriver
     */
    public EmployeeDetailsPage clickShowDetailsLink(String identification){
        driver.findElement(By.xpath(String.format("%s%s%s", "//td[text()='", identification, "']/following-sibling::td/a[text()='Show details']"))).click();

        return new EmployeeDetailsPage(driver);
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
