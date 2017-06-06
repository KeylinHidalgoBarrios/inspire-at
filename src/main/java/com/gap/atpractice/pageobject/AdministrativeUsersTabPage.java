package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/26/2017.
 */
public class AdministrativeUsersTabPage extends PageBase {

    //Page url
    private final String url = "users";

    //Web elements
    @FindBy (xpath = "//h1[text()='Listing All Administrative Users']") private WebElement userTabTitle;

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public AdministrativeUsersTabPage(WebDriver driver){
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
        Assert.assertTrue(url.contains("new"), "Not on Administrative Users tab: ".concat(url));
    }
}
