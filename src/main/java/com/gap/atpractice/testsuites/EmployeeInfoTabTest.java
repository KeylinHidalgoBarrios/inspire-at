package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.testsuites.Login.LoginTestCommons;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
public class EmployeeInfoTabTest extends TestBase {
    /**
     * Click new employee link
     * @param email email to login
     * @param password password to login
     */
    @Test(groups = "employeeTests", priority = 1)
    @Parameters({"email", "password"})
    public void clickNewEmployeeLink(String email, String password){
        try {
            HomePage homePage = LoginTestCommons.login(email, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
            Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page cannot be displayed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Click Employee Details link from a user within the table
     * @param email email to login
     * @param password password to login
     */
    @Test(groups = "employeeTests", priority = 3)
    @Parameters({"email", "password", "identificationNewEmployee"})
    public void clickEmployeeDetails(String email, String password, String identification){
        try {
            HomePage homePage = LoginTestCommons.login(email, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(driver).clickShowDetailsLink(identification);
            Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee Details page cannot be displayed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(groups = "employeeTests", priority = 4)
    @Parameters({"email", "password", "identificationNewEmployee"})

    public void deleteNewEmployee(String email, String password, String identification){
        try {
            HomePage homePage = LoginTestCommons.login(email, password);
            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            boolean deleted = new EmployeesInfoTabPage(driver).deleteEmployee(identification);
            Assert.assertFalse(deleted, "User was not deleted");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
