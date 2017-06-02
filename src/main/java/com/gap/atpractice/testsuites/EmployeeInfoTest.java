package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.EmployeesInfoPage;
import com.gap.atpractice.pageobject.HomePage;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.pageobject.NewEmployeePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
public class EmployeeInfoTest extends TestBase {
    private LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    public void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    @Test(groups = "newEmployee1")
    @Parameters({"userName", "password"})
    public void clickNewEmployeeLinkCompleteCycle(String userName, String password){
        try {
            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            NewEmployeePage newEmployeePage = new EmployeesInfoPage(driver).clickNewEmployeeLink();
            Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page cannot be displayed");

            quitBrowser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
