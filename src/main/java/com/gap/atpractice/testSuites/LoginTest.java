package com.gap.atpractice.testSuites;

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
        driver = seleniumBase.setup("IE");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://vacations.evercoding.com/users");
    }

    public static void main(String [] args){
        try {
            //Initialize driver
            initSetup();

            //Validating if page is displayed
            boolean isPageDisplayed = driver.findElement(By.tagName("title")).isDisplayed();

            if (isPageDisplayed)
                System.out.println("Login page is displayed");

            //Finding elements
            driver.findElement(By.id("user_email")).sendKeys("at_java_training@wearegap.com");
            driver.findElement(By.id("user_password")).sendKeys("123queso");

            String url = "./src/main/resources/screenshots/screenshot1.png";
            //Taking the screenshot before hitting login button
            TakeScreenshot.takeScreenshot(driver, url,"png");

            driver.findElement(By.xpath("//input[@class='submit']")).click();

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
