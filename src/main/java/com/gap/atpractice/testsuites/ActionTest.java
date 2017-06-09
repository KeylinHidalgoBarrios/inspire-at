package com.gap.atpractice.testsuites;

import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by keyhi on 5/4/2017.
 */
public class ActionTest {
    private static WebDriver driver;

    private static void initSetup(){
        SeleniumBase seleniumBase = new SeleniumBase();
        //driver = seleniumBase.setup("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://jsfiddle.net/L6qggtub/2/show/");
        WebElement iframe = driver.findElement(By.cssSelector("iframe"));
        driver.switchTo().frame(iframe);
    }

    public static void main(String [] args) {
        try {
            //Initialize driver
            initSetup();

            Actions act = new Actions(driver);
            act.doubleClick(driver.findElement(By.id("double_click"))).build().perform();
            Thread.sleep(1000);

            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));

            act.dragAndDrop(source, target).perform();
            Thread.sleep(1000);

            WebElement textArea = driver.findElement(By.cssSelector("textarea"));
            textArea.click();
            act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
            Thread.sleep(1000);

            WebElement menuFather = driver.findElement(By.className("dropbtn"));
            WebElement menu1 = driver.findElement(By.xpath("//*[@class='dropdown-content']/a[1]"));
            WebElement menu2 = driver.findElement(By.xpath("//*[@class='dropdown-content']/a[2]"));
            WebElement menu3 = driver.findElement(By.xpath("//*[@class='dropdown-content']/a[3]"));

            act.moveToElement(menuFather).build().perform();
            Thread.sleep(1000);
            act.moveToElement(menu1).build().perform();
            Thread.sleep(1000);
            act.moveToElement(menu2).build().perform();
            Thread.sleep(1000);
            act.moveToElement(menu3).build().perform();
            Thread.sleep(1000);

        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
