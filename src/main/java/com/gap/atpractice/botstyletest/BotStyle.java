package com.gap.atpractice.botstyletest;

import com.google.common.base.Function;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by keyhi on 5/18/2017.
 */
public class BotStyle {

    //Web driver
    private WebDriver driver;

    /**
     * Constructor method
     * @param driver webdriver
     */
    public BotStyle(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Wait for the title to display so before calling the title it is sure that it already loaded
     * @param timeToWaitSecs time in seconds
     * @param title desired title
     */
    public void waitForPageTitle(int timeToWaitSecs, final String title){

        (new WebDriverWait(driver, timeToWaitSecs)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(title.toLowerCase());
            }
        });
    }

    /**
     * Waiting for the element to be present before using it
     * @param byElement Locator type By
     * @param timeoutInSeconds timeout in seconds
     * @return return a webelement as a result of the By element
     */
    public WebElement waitForElementPresent(int timeoutInSeconds, final By byElement){
        return waitForElementPresent(timeoutInSeconds, driver.findElement(byElement));
    }


    /**
     * Waiting for the element to be present before using it
     * @param element Webelement
     * @param timeoutInSeconds timeout in seconds
     * @return WebElement
     */
    public WebElement waitForElementPresent(int timeoutInSeconds, final WebElement element){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutInSeconds);

        WebElement we= wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
        return we;
    }

    /**
     * Type desired value, this method ensures that the element is present and cleans the field before sending the keys
     * @param element Desired web element
     * @param text Desired text
     */
    public void type(WebElement element, String text){
        waitForElementPresent(60, element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Type desired value, this method ensures that the element is present and cleans the field before sending the keys
     * @param locator Desired locator type by
     * @param text Desired text
     */
    public void type(By locator, String text){
        WebElement element = waitForElementPresent(60, locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Select an item from a list
     * @param element List element
     * @param text text to be selected
     */
    public void selectListValue(WebElement element, String text){
        waitForElementPresent(60, element);
        new Select(element).selectByVisibleText(text);
    }

    /**
     * Select an item from a list
     * @param element List element
     * @param text text to be selected
     */
    public void selectListValue(By element, String text){
        selectListValue(driver.findElement(element), text);
    }

    /**
     * Click an element
     * @param element Web element to perform operation
     */
    public void clickElement(By element){
        waitForElementPresent(60, element);
        driver.findElement(element).click();
    }

    /**
     * Click alert options
     * @param confirm if accepting this is true, if dismissing this is false
     */
    public void clickAlertOption(boolean confirm){
        Alert alert = driver.switchTo().alert();

        if(confirm)
            alert.accept();
        else
            alert.dismiss();
    }

    /**
     * Validate if an element is present in the page
     * @param element element to be validated
     * @return true if element is present, false if element is not
     */
    public boolean isElementPresent(WebElement element){
        try{
            waitForElementPresent(60, element);
            element.isDisplayed();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Validate if an element is present in the page
     * @param element element to be validated
     * @return true if element is present, false if element is not
     */
    public boolean isElementPresent(By element){
        try{
            waitForElementPresent(60, element);
            driver.findElement(element).isDisplayed();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
