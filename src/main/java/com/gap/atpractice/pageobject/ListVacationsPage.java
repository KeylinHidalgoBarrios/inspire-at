package com.gap.atpractice.pageobject;

import com.gap.atpractice.framework.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/7/2017.
 */
public class ListVacationsPage extends PageBase {
    @FindBy(xpath = "//h1[contains(text(),'Listing vacations for employee:')]") private WebElement pageTitle;

    /**
     * Initialize driver and page elements with PageFactory
     * @param driver WebDriver across pages
     */
    public ListVacationsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
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
        Assert.assertTrue(url.contains("users"), "Not on Employee Details page: ".concat(url));
    }
}
