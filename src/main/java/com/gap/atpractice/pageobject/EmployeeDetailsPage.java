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
public class EmployeeDetailsPage extends PageBase {

    //Web elements
    @FindBy(xpath = "//h1[text() = 'Employee vacations report']") private WebElement vacationsTitle;
    @FindBy(xpath = "//a[text()='Back']") private  WebElement backLink;
    @FindBy(xpath = "//a[text()='Add/Request Vacations']") private WebElement addRequestVacationLink;
    @FindBy(id = "notice") private WebElement noticeMessageElement;

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public EmployeeDetailsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Confirm that the page loaded
     * @return true if loaded, false if it's not
     */
    public boolean isPageLoaded (){
        botStyle.waitForElementPresent(vacationsTitle, 60);
        return vacationsTitle.isDisplayed();
    }

    /**
     * Click back link to go to Employee Info tab to see the list of employees
     * @return instance of EmployeesInfoTabPage
     */
    public EmployeesInfoTabPage clickBackLink(){
        botStyle.click(backLink);

        return PageFactory.initElements(driver, EmployeesInfoTabPage.class);
    }

    /**
     * Click 'Add/Request Vacations' link to go to vacations page
     * @return instance of AddRequestVacationPage which is the page displayed after clicking the link
     */
    public AddRequestVacationPage clickAddRequestVacationLink(){
        botStyle.click(addRequestVacationLink);

        return PageFactory.initElements(driver, AddRequestVacationPage.class);
    }

    /**
     * Verify if message is displayed in the page
     * @param message Message to compare with web element's text
     * @return True if it's displayed, false if it's other message what's being displayed
     */
    public boolean isMessageDisplayed(String message){
        return noticeMessageElement.getText().equals(message);
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
        Assert.assertTrue(url.contains("vacations"), "Not on Add/Request Vacations page: ".concat(url));
    }
}
