package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
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

    @Test(groups = "newEmployee")
    @Parameters({"userName", "password", "firstNameNewEmployee", "lastNameNewEmployee", "emailNewEmployee", "identificationNewEmployee",
            "leaderNameNewEmployee", "yearNewEmployee", "monthNewEmployee", "dayNewEmployee"})
    public void createNewEmployee(String userName, String password, String firstName, String lastName, String email, String identification,
                                  String leaderName, String year, String month, String day){
        try {
            goToLoginPage();

            HomePage homePage = loginPage.loginValidCredentials(userName, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
            Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page cannot be displayed");

            EmployeeDetailsPage employeeDetailsPage = newEmployeePage.createNewEmployee(firstName, lastName, email, identification, leaderName, year, month, day);
            Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "New Employee page cannot be displayed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
