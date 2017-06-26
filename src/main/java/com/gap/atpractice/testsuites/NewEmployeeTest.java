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
    LoginTestCommon loginTestCommon;

    public NewEmployeeTest(){
        loginTestCommon = new LoginTestCommon();
    }

    @Test(groups = "employeeTests001", priority = 1)
    @Parameters({"email", "password",
            "newEmployeeFirstName", "newEmployeeLastName", "newEmployeeEmail", "newEmployeeId", "newEmployeeLeadName",
            "newEmployeeStartWorkingYear", "newEmployeeStartWorkingMonth", "newEmployeeStartWorkingDay", "idCreateNewEmployee"})
    public void createNewEmployee(String email, String password, String newEmployeeFirstName, String newEmployeeLastName, String newEmployeeEmail,
                                  String newEmployeeId, String newEmployeeLeadName, String startWorkingYear, String startWorkingMonth, String startWorkingDay,
                                  String testCaseId){
        //Login
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        NewEmployeePage newEmployeePage = new EmployeesInfoTabPage(getDriver()).clickNewEmployeeLink();
        Assert.assertTrue(newEmployeePage.isPageLoaded(), "New Employee page not displayed");

        //Create the user
        EmployeeDetailsPage employeeDetailsPage = newEmployeePage.createNewEmployee(newEmployeeFirstName, newEmployeeLastName, newEmployeeEmail,
                newEmployeeId, newEmployeeLeadName, startWorkingYear, startWorkingMonth, startWorkingDay);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        //Go to users list
        EmployeesInfoTabPage employeesInfoTabPage = employeeDetailsPage.clickBackLink();
        Assert.assertTrue(employeesInfoTabPage.isPageLoaded(), "Employee Information tab not displayed");

        //Validate if new created user displays
        boolean userExists = employeesInfoTabPage.userExists(newEmployeeId);
        Assert.assertTrue(userExists, "User not created");
    }
}
