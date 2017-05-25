package com.gap.atpractice.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/24/2017.
 */
public class ForgotPasswordPage extends PageBase{

    private String url = "";

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
        this.driver.get(super.URL_BASE);
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded(){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("users"), "Not on the issue entry page: "+url);
    }
}
