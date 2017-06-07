package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.common.LoginTestCommon;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/2/2017.
 */
@Test(groups = {"regression", "employeeTests"})
public class NewEmployeeTest extends TestBase{
    @Test(groups = "employeeTests001", priority = 1)
    @Parameters({"email", "password", "credsNewEmployeeCreation"})
    public void createNewEmployee(String email, String password, String credentials){
        String[] userInfo = credentials.split(",");

        //Login
        HomePage homePage = LoginTestCommon.login(email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(driver).clickNewEmployeeLink();
        Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page not displayed");

        //Create the user
        EmployeeDetailsPage employeeDetailsPage = newEmployeePage.createNewEmployee(userInfo[0], userInfo[1], userInfo[2], userInfo[3],
        userInfo[4], userInfo[5], userInfo[6], userInfo[7]);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");
    }
}
