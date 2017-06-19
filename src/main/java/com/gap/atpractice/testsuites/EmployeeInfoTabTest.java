package com.gap.atpractice.testsuites;

import com.gap.atpractice.framework.TestBase;
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
    LoginTestCommon loginTestCommon;

    public EmployeeInfoTabTest(){
        loginTestCommon = new LoginTestCommon();
    }

    /**
     * Click Employee Details link from a user within the table
     * @param email email to login
     * @param password password to login
     */
    @Test(groups = "employeeTests002", priority = 2)
    @Parameters({"email", "password", "identificationNewEmployee", "idClickEmployeeDetails"})
    public void clickEmployeeDetails(String email, String password, String identification, String id){
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(getDriver()).clickShowDetailsLink(identification);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee Details page cannot be displayed");
    }

    /**
     * Delete employee from Employee Information tab
     * @param email email to login
     * @param password password to login
     * @param identification id of the employee to be deleted
     */
    @Test(groups = "employeeTests009", priority = 9)
    @Parameters({"email", "password", "identificationNewEmployee", "idDeleteEmployee"})
    public void deleteEmployee(String email, String password, String identification, String id){
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        boolean deleted = new EmployeesInfoTabPage(getDriver()).deleteEmployee(identification);
        Assert.assertTrue(deleted, "User was not deleted");
    }
}
