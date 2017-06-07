package com.gap.atpractice.common;

import com.gap.atpractice.pageobject.HomePage;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.testsuites.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

/**
 * Created by keyhi on 6/5/2017.
 */
public class LoginTestCommon extends TestBase {
    private static LoginPage loginPage;

    /**
     * Common method to login into the application
     * @param email email of the user
     * @param password password of the user
     * @return HomePage instance
     */
    public static HomePage login(String email, String password){
        loginPage = (LoginPage) new LoginPage(driver).get();
        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");

        return loginPage.loginValidCredentials(email, password);
    }
}
