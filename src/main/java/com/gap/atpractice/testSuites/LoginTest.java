package com.gap.atpractice.testSuites;

import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.selenium.SeleniumBase;
import com.gap.atpractice.utils.TakeScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by auto on 06/04/17.
 */
public class LoginTest {
    private static WebDriver driver;

    private static void initSetup(){
        SeleniumBase seleniumBase = new SeleniumBase();
        driver = seleniumBase.setup("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void main(String [] args){
        try {
            //Initialize driver
            initSetup();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.navigateToLoginPage("http://vacations.evercoding.com/users");

            if (loginPage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"))
                System.out.println("Login page is displayed");

            HomePage homePage = loginPage.loginValidCredentials("at_java_training@wearegap.com", "123queso");

            if (homePage.isPageLoaded("Vacations Management Site - Growth Acceleration Partners"))
                System.out.println("Home page is displayed");

            //Taking the screenshot after Welcome page loads
            TakeScreenshot.takeScreenshot(driver, "./src/main/resources/screenshots/screenshot2.png", "png");

            //Using javascript executor to validate the page is ready
            JavascriptExecutor js = (JavascriptExecutor)driver;
            if(js.executeScript("return document.readyState").equals("complete")){
                System.out.println("JavascriptExecutor document loaded");
            }

            //Finding 4 elements using xpath
            /*driver.findElement(By.xpath("//*[@class='appdashboard-panel appadmin']"));
            System.out.println("First xpath element present");

            driver.findElement(By.xpath("//*[@class='appdashboard-panel appinternalsettings']"));
            System.out.println("Second xpath element present");

            driver.findElement(By.xpath("//*[@class='appdashboard-panel appadoption']"));
            System.out.println("Third xpath element present");

            driver.findElement(By.xpath("//*[@class='appdashboard-panel appconfigurationpanel']"));
            System.out.println("Fourth xpath element present");

            //Finding 2 elements using cssSelector
            driver.findElement(By.cssSelector("[class='appdashboard-panel appconfigurationpanel']"));
            System.out.println("First CSS element present");

            driver.findElement(By.cssSelector("[class='appdashboard-panel appadoption']"));
            System.out.println("Second CSS element present");*/

            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
