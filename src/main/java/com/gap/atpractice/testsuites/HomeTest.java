package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.pageobject.LoginPage;
import com.gap.atpractice.testsuites.Login.LoginTestCommons;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by keyhi on 5/26/2017.
 */
public class HomeTest extends TestBase {
    /**
     * Go to Administrative Users tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"email", "password"})
    public void clickAdministrativeUsersTab(String email, String password){
        try{
            //Home page loaded
            HomePage homePage = LoginTestCommons.login(email, password);

            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            //Click Administrative Users tab
            AdministrativeUsersTabPage administrativeUsersTabPage = homePage.clickAdminUsersTab();

            Assert.assertTrue(administrativeUsersTabPage.isPageLoaded(), "Administrative Users tab can not load");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Go to Employee Information tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"email", "password"})
    public void clickEmployeeInfoTab(String email, String password){
        try{
            //Home page loaded
            HomePage homePage = LoginTestCommons.login(email, password);

            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            //Click Employees Information tab
            EmployeesInfoTabPage employeesInfoTabPage = homePage.clickEmployeeInfoTab();

            Assert.assertTrue(employeesInfoTabPage.isPageLoaded(), "Employees Information tab can not load");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Go to My Account tab in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"email", "password"})
    public void clickMyAccountTab(String email, String password){
        try{
            //Home page loaded
            HomePage homePage = LoginTestCommons.login(email, password);

            Assert.assertTrue(homePage.isPageLoaded(), "Home page cannot be displayed");

            //Click My Account tab
            MyAccountTabPage myAccountTabPage = homePage.clickMyAccountTab();

            Assert.assertTrue(myAccountTabPage.isPageLoaded(), "My Account tab can not load");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Click through all tabs in home page
     * @param email User name for login
     * @param password Password for login
     */
    @Test(groups = {"allTabsTest", "regression"})
    @Parameters({"email", "password"})
    public void goThroughAllTabs(String email, String password){
        try{
            //Home page loaded
            HomePage homePage = LoginTestCommons.login(email, password);

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
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
