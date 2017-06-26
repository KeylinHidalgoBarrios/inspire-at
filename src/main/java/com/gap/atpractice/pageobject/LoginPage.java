package com.gap.atpractice.pageobject;

import com.gap.atpractice.framework.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by keyhi on 5/15/2017.
 */
public class LoginPage extends PageBase {

    //Page url
    private final String url = "users/sign_in";

    //Web elements
    @FindBy(id = "user_email") private WebElement userName;
    @FindBy(id = "user_password") private WebElement password;
    @FindBy(xpath = "//input[@class='submit']") private WebElement loginButton;
    @FindBy(xpath = "//a[text()='Forgot your password?']") private WebElement forgotPasswordLink;

    /**
     * Constructor of the page
     * @param driver receives driver accross application
     */
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Fill email and password fields with values
     * @param email user email to login
     * @param password user password to login
     */
    public void insertCredentials(String email, String password){
        botStyle.type(this.userName, email);
        botStyle.type(this.password, password);
    }

    /**
     * Submit login information
     */
    public HomePage submitInformation(){
        botStyle.click(loginButton);

        return new HomePage(driver);
    }

    /**
     * Redirect to page to reset password
     * @return instance of the NewPasswordPage
     */
    public ForgotPasswordPage goToNewPasswordPage(){

        botStyle.click(forgotPasswordLink);

        return PageFactory.initElements(driver, ForgotPasswordPage.class);
    }

    /**
     * Method to login with valid credentials
     * @param title page title
     * @return return true if the Login page is loaded
     */
    public boolean isPageLoaded (String title){
        botStyle.waitForPageTitle(title, 60);
        return driver.getTitle().equals(title);
    }

    /**
     * Overriding load method from LoadableComponent
     */
    @Override
    protected void load(){
        this.driver.get(super.URL_BASE.concat(this.url));
    }

    /**
     * Overriding isLoaded method from LoadableComponent
     */
    @Override
    protected void isLoaded(){
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("users"), "Not on Login page: ".concat(url));
    }
}
