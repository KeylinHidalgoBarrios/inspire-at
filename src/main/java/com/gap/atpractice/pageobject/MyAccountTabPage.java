package com.gap.atpractice.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by Key on 6/1/2017.
 */
public class MyAccountTabPage extends PageBase {
    //Page url
    private final String url = "my_account";

    //Web elements
    @FindBy(xpath = "//h2[contains(text(),'Edit My Account')]") private WebElement userTabTitle;

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public MyAccountTabPage(WebDriver driver){
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
        Assert.assertTrue(url.contains("new"), "Not on My Account tab: ".concat(url));
    }
}

