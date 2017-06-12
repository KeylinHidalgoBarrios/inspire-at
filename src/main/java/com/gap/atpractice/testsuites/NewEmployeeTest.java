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
@Test(groups = {"regression", "employeeTests"})
public class NewEmployeeTest extends TestBase {
    @Test(groups = "employeeTests001", priority = 1)
    @Parameters({"email", "password", "credsNewEmployeeCreation"})
    public void createNewEmployee(String email, String password, String credentials){
        //Login
        HomePage homePage = LoginTestCommon.login(email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
        Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page not displayed");

        //Create the user
        String[] userInfo = credentials.split(",");
        EmployeeDetailsPage employeeDetailsPage = newEmployeePage.createNewEmployee(userInfo);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        //Go to users list
        EmployeesInfoTabPage employeesInfoTabPage = employeeDetailsPage.clickBackLink();
        Assert.assertTrue(employeesInfoTabPage.isPageLoaded(), "Employee Information tab not displayed");

        //Validate if new created user displays
        boolean userExists = employeesInfoTabPage.userExists(userInfo[3]);
        Assert.assertTrue(userExists, "User not created");
    }
}
