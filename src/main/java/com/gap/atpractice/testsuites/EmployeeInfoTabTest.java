package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
public class EmployeeInfoTabTest extends TestBase {
    private LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    public void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    /**
     * Click new employee link
     * @param userName userName to login
     * @param password password to login
     */
    @Test(groups = "newEmployee")
    @Parameters({"userName", "password"})
    public void clickNewEmployeeLink(String userName, String password){
        try {
            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
            Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page cannot be displayed");

            quitBrowser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Click Employee Details link from a user within the table
     * @param userName userName to login
     * @param password password to login
     */
    @Test(groups = "employeeDetails")
    @Parameters({"userName", "password", "identificationNewEmployee"})
    public void clickEmployeeDetails(String userName, String password, String identification){
        try {
            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(driver).clickShowDetailsLink(identification);
            Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee Details page cannot be displayed");

            quitBrowser();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
