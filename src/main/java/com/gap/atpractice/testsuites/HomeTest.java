package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.common.LoginTestCommon;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by keyhi on 5/26/2017.
 */
@Test(groups = {"regression", "homePageTabsTest"})
public class HomeTest extends TestBase {
    /**
     * Go to Administrative Users tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = "homePageTabsTest001")
    @Parameters({"email", "password"})
    public void clickAdministrativeUsersTab(String email, String password){
        //Home page loaded
        HomePage homePage = LoginTestCommon.login(email, password);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        //Click Administrative Users tab
        AdministrativeUsersTabPage administrativeUsersTabPage = homePage.clickAdminUsersTab();

        Assert.assertTrue(administrativeUsersTabPage.isPageLoaded(), "Administrative Users tab can not load");
    }

    /**
     * Go to Employee Information tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = "homePageTabsTest002")
    @Parameters({"email", "password"})
    public void clickEmployeeInfoTab(String email, String password){
        //Home page loaded
        HomePage homePage = LoginTestCommon.login(email, password);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        //Click Employees Information tab
        EmployeesInfoTabPage employeesInfoTabPage = homePage.clickEmployeeInfoTab();

        Assert.assertTrue(employeesInfoTabPage.isPageLoaded(), "Employees Information tab can not load");
    }

    /**
     * Go to My Account tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = "homePageTabsTest003")
    @Parameters({"email", "password"})
    public void clickMyAccountTab(String email, String password){
        //Home page loaded
        HomePage homePage = LoginTestCommon.login(email, password);

        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        //Click My Account tab
        MyAccountTabPage myAccountTabPage = homePage.clickMyAccountTab();

        Assert.assertTrue(myAccountTabPage.isPageLoaded(), "My Account tab can not load");
    }

    /**
     * Click through all tabs in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = "homePageTabsTest004")
    @Parameters({"email", "password"})
    public void goThroughAllTabs(String email, String password){
        //Home page loaded
        HomePage homePage = LoginTestCommon.login(email, password);
        Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

        //Go to My Account tab
        MyAccountTabPage myAccountTabPage = homePage.clickMyAccountTab();
        Assert.assertTrue(myAccountTabPage.isPageLoaded(), "My Account tab can not load");

        //Go to Administrative Users tab
        AdministrativeUsersTabPage administrativeUsersTabPage = homePage.clickAdminUsersTab();
        Assert.assertTrue(administrativeUsersTabPage.isPageLoaded(), "My Account tab can not load");

        //Go to Employees Info
        EmployeesInfoTabPage employeesInfoTabPage = homePage.clickEmployeeInfoTab();
        Assert.assertTrue(employeesInfoTabPage.isPageLoaded(), "My Account tab can not load");
    }
}
