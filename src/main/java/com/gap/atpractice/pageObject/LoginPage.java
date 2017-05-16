package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by keyhi on 5/15/2017.
 */
public class LoginPage {

    //Using same driver across application
    WebDriver driver;

    //Elements locators using Page Factory
    @FindBy(id = "user_email") private WebElement userName;
    @FindBy(id = "user_password") private WebElement password;
    @FindBy(xpath = "//input[@class='submit']") private WebElement loginButton;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to navigate to site
     * @param url URL to navigate to
     */
    public void navigateToLoginPage(String url){
        driver.get(url);
    }

    /**
     * Returning the page title
     * @return Title
     */
    public String getPageTitle(){
        return driver.getTitle();
    }

    /**
     * Method to login with valid credentials
     * @param title page title
     * @return return true if the Login page is loaded
     */
    public Boolean isPageLoaded (String title){
        return getPageTitle().equals(title);
    }

    /**
     * Method to login with valid credentials
     * @param userName user name to login
     * @param password password to login
     * @return return HomePage which is the page where user is redirected after successful login
     */
    public HomePage loginValidCredentials (String userName, String password){

        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        loginButton.click();

        return new HomePage(driver);
    }
}
