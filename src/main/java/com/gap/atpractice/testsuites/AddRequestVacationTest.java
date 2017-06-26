package com.gap.atpractice.testsuites;

import com.gap.atpractice.common.LoginTestCommon;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.pageobject.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Key on 6/7/2017.
 */
@Test(groups = {"employeeTests", "regression"})
public class AddRequestVacationTest extends TestBase {
    LoginTestCommon loginTestCommon;

    public AddRequestVacationTest(){
        loginTestCommon = new LoginTestCommon();
    }

    @Test(groups = "employeeTests003", priority = 3)
    @Parameters({"email", "password",
            "addVacationType", "addVacationSince", "addVacationUntil", "addVacationRequestedOn", "addVacationTotalDays", "addVacationDescription",
            "vacationsCreatedMessage", "newEmployeeId", "idAddVacationDays"})
    public void addVacationDays(String email, String password,
                                String type, String since, String until, String requestedOn, String totalDays, String description,
                                String vacationsCreatedMessage, String employeeId, String testCaseId){
        //Login
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(getDriver()).clickShowDetailsLink(employeeId);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        AddRequestVacationPage addRequestVacationPage = employeeDetailsPage.clickAddRequestVacationLink();
        Assert.assertTrue(addRequestVacationPage.isPageLoaded(), "Add/Request Vacations page not displayed");

        employeeDetailsPage = addRequestVacationPage.addVacationDays(type, since, until, requestedOn, totalDays, description);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        Assert.assertTrue(employeeDetailsPage.isMessageDisplayed(vacationsCreatedMessage));
    }

    @Test(groups = "employeeTests004", priority = 4)
    @Parameters({"email", "password",
            "deductVacationType", "deductVacationSince", "deductVacationUntil", "deductVacationRequestedOn", "deductVacationTotalDays", "deductVacationDescription",
            "vacationsCreatedMessage", "newEmployeeId", "idDeductVacationDays"})
    public void deductVacationDays(String email, String password,
                                   String type, String since, String until, String requestedOn, String totalDays, String description,
                                   String vacationsCreatedMessage, String employeeId, String testCaseId){

        //Login
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(getDriver()).clickShowDetailsLink(employeeId);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        AddRequestVacationPage addRequestVacationPage = employeeDetailsPage.clickAddRequestVacationLink();
        Assert.assertTrue(addRequestVacationPage.isPageLoaded(), "Add/Request Vacations page not displayed");

        employeeDetailsPage = addRequestVacationPage.addVacationDays(type, since, until, requestedOn, totalDays, description);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        Assert.assertTrue(employeeDetailsPage.isMessageDisplayed(vacationsCreatedMessage));
    }
}
