package com.gap.atpractice.common;

import com.gap.atpractice.pageobject.HomePage;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.framework.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by keyhi on 6/5/2017.
 */
public class LoginTestCommon {
    private LoginPage loginPage;

    /**
     * Common method to login into the application
     * @param email email of the user
     * @param password password of the user
     * @return HomePage instance
     */
    public HomePage login(WebDriver driver, String email, String password){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");

        loginPage.insertCredentials(email, password);

        return loginPage.submitInformation();
    }
}
