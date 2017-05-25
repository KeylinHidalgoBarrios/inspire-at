package com.gap.atpractice.pageObject;

import com.gap.atpractice.botStyleTest.BotStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

/**
 * Created by keyhi on 5/15/2017.
 */
public class LoginPage extends LoadableComponent<LoginPage>{

    //Using same driver across application
    WebDriver driver;

    BotStyle botStyle;

    //Elements locators using Page Factory
    @FindBy(id = "user_email") private WebElement userName;
    @FindBy(id = "user_password") private WebElement password;
    @FindBy(xpath = "//input[@class='submit']") private WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(), 'password?')]") private WebElement forgotPasswordLink;

    /**
     * Constructor
     * @param driver Web driver across application
     */
    public LoginPage(WebDriver driver){
        this.driver = driver;
        botStyle = new BotStyle(this.driver);
        PageFactory.initElements(this.driver, this);
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
        botStyle.waitForPageTitle(60, title);
        return getPageTitle().equals(title);
    }

    /**
     * Method to login with valid credentials
     * @param userName user name to login
     * @param password password to login
     * @return return HomePage which is the page where user is redirected after successful login
     */
    public HomePage loginValidCredentials (String userName, String password){

        botStyle.type(this.userName, userName);
        botStyle.type(this.password, password);
        this.loginButton.click();

        return new HomePage(driver);
    }

    /**
     * Redirect to page to reset password
     * @return instance of the NewPasswordPage
     */
    public ForgotPasswordPage goToNewPasswordPage(){

        this.forgotPasswordLink.click();

        return new ForgotPasswordPage(driver);
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
        this.driver.get("https://vacations-management.herokuapp.com/users/sign_in");
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
