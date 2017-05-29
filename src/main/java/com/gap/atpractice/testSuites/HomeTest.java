package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageObject.AdministrativeUsersPage;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by keyhi on 5/26/2017.
 */
public class HomeTest extends TestBase {
    private LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    public void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    /**
     * Go to Administrative Users tab in home page
     * @param userName User name for login
     * @param password Password for login
     */
    @Test(groups = {"tabsTest", "regression"})
    @Parameters({"userName", "password"})
    public void clickAdministrativeUsersTab(String userName, String password){
        try{
            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);

            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            AdministrativeUsersPage administrativeUsersPage = homePage.clickAdminUsersTab();

            Assert.assertTrue(administrativeUsersPage.isPageLoaded(), "Page can not load");

            quitBrowser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
