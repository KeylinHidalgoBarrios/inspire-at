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
    @Parameters({"email", "password", "infoAddVacationDays", "vacationsCreatedMessage", "externalIdAddVacationDays", "idAddVacationDays"})
    public void addVacationDays(String email, String password, String vacationInformation, String vacationsCreatedMessage, String externalId, String id){
        String[] vacationInfo = vacationInformation.split(",");

        //Login
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(getDriver()).clickShowDetailsLink(vacationInfo[6]);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        AddRequestVacationPage addRequestVacationPage = employeeDetailsPage.clickAddRequestVacationLink();
        Assert.assertTrue(addRequestVacationPage.isPageLoaded(), "Add/Request Vacations page not displayed");

        employeeDetailsPage = addRequestVacationPage.addVacationDays(vacationInfo);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        Assert.assertTrue(employeeDetailsPage.isMessageDisplayed(vacationsCreatedMessage));
    }

    @Test(groups = "employeeTests004", priority = 4)
    @Parameters({"email", "password", "infoDeductVacationDays", "vacationsCreatedMessage", "externalIdDeductVacationDays", "idDeductVacationDays"})
    public void deductVacationDays(String email, String password, String vacationInformation, String vacationsCreatedMessage, String externalId, String id){
        String[] vacationInfo = vacationInformation.split(",");

        //Login
        HomePage homePage = loginTestCommon.login(getDriver(), email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page not displayed");

        //Go to Employee creation page
        EmployeeDetailsPage employeeDetailsPage = new EmployeesInfoTabPage(getDriver()).clickShowDetailsLink(vacationInfo[6]);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        AddRequestVacationPage addRequestVacationPage = employeeDetailsPage.clickAddRequestVacationLink();
        Assert.assertTrue(addRequestVacationPage.isPageLoaded(), "Add/Request Vacations page not displayed");

        employeeDetailsPage = addRequestVacationPage.addVacationDays(vacationInfo);
        Assert.assertTrue(employeeDetailsPage.isPageLoaded(), "Employee details page not displayed");

        Assert.assertTrue(employeeDetailsPage.isMessageDisplayed(vacationsCreatedMessage));
    }
}
