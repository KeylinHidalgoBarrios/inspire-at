package com.gap.atpractice.pageobject;

import com.gap.atpractice.botstyletest.BotStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

/**
 * Created by keyhi on 5/25/2017.
 */
public abstract class PageBase extends LoadableComponent {

    //URL base, most classes that extend from this one user the url base and add the rest of the url to redirect to that specific page
    protected String URL_BASE = "https://vacations-management.herokuapp.com/";

    //Using same driver across application
    protected WebDriver driver;
    protected BotStyle botStyle;

    /**
     * Constructor of the page
     * @param driver Webdriver accross all application
     */
    public PageBase(WebDriver driver){
        this.driver = driver;
        botStyle = new BotStyle(this.driver);
    }
}
