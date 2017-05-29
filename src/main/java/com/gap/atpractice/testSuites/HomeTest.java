package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageObject.AdministrativeUsersPage;
import com.gap.atpractice.pageObject.HomePage;
import org.testng.Assert;

/**
 * Created by keyhi on 5/26/2017.
 */
public class HomeTest extends TestBase {
    private HomePage homePage;

    public clickAdministrativeUsersTab(){
        try{
            AdministrativeUsersPage administrativeUsersPage = homePage.clickAdminUsersTab();

            Assert.assertTrue(administrativeUsersPage.isPageLoaded(""), "Page can not load");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
