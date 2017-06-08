package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.common.LoginTestCommon;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
@Test(groups = {"employeeTests", "regression"})
public class EmployeeInfoTabTest extends TestBase {
    /**
     * Click Employee Details link from a user within the table
     * @param email email to login
     * @param password password to login
     */
    @Test(groups = "employeeTests002", priority = 2)
    @Parameters({"email", "password", "identificationNewEmployee"})
    public void clickEmployeeDetails(String email, String password, String identification){
        HomePage homePage = LoginTestCommon.login(email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(driver).clickShowDetailsLink(identification);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee Details page cannot be displayed");
    }

    /**
     * Delete employee from Employee Information tab
     * @param email email to login
     * @param password password to login
     * @param identification id of the employee to be deleted
     */
    @Test(groups = "employeeTests004", priority = 4)
    @Parameters({"email", "password", "identificationNewEmployee"})
    public void deleteEmployee(String email, String password, String identification){
        HomePage homePage = LoginTestCommon.login(email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        boolean deleted = new EmployeesInfoTabPage(driver).deleteEmployee(identification);
        Assert.assertFalse(deleted, "User was not deleted");
    }
}
