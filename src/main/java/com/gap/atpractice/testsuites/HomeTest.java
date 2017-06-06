package com.gap.atpractice.testsuites;

import com.gap.atpractice.pageobject.*;
import com.gap.atpractice.pageobject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by keyhi on 5/26/2017.
 */
public class HomeTest extends TestBase {
    private LoginPage loginPage;

    /**
     * General method to go to Login Page
     */
    public void goToLoginPage(){
        loginPage = (LoginPage) new LoginPage(driver).get();

        Assert.assertTrue(loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"), "Login page cannot be displayed");
    }

    /**
     * Go to Administrative Users tab in home page
     * @param userName User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"userName", "password"})
    public void clickAdministrativeUsersTab(String userName, String password){
        try{
            goToLoginPage();

            //Home page loaded
            HomePage homePage = loginPage.loginValidCredentials(userName, password);

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
     * @param userName User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"userName", "password"})
    public void clickEmployeeInfoTab(String userName, String password){
        try{
            goToLoginPage();

            //Home page loaded
            HomePage homePage = loginPage.loginValidCredentials(userName, password);

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
     * @param userName User name for login
     * @param password Password for login
     */
    @Test(groups = {"individualTabsTest", "regression"})
    @Parameters({"userName", "password"})
    public void clickMyAccountTab(String userName, String password){
        try{
            goToLoginPage();

            //Home page loaded
            HomePage homePage = loginPage.loginValidCredentials(userName, password);

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
     * @param userName User name for login
     * @param password Password for login
     */
    @Test(groups = {"allTabsTest", "regression"})
    @Parameters({"userName", "password"})
    public void goThroughAllTabs(String userName, String password){
        try{
            goToLoginPage();

            //Home page loaded
            HomePage homePage = loginPage.loginValidCredentials(userName, password);

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
