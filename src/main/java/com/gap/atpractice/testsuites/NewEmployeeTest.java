package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
public class NewEmployeeTest extends TestBase{
    private LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    public void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    @Test(groups = "employeeTests", priority = 2)
    @Parameters({"email", "password", "credsNewEmployeeCreation"})
    public void createNewEmployee(String email, String password, String credentials){
        try {
            String[] userInfo = credentials.split(",");

            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(email, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
            Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page cannot be displayed");

            EmployeeDetailsPage employeeDetailsPage = newEmployeePage.createNewEmployee(userInfo[0], userInfo[1], userInfo[2], userInfo[3],
            userInfo[4], userInfo[5], userInfo[6], userInfo[7]);
            Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "New Employee page cannot be displayed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
