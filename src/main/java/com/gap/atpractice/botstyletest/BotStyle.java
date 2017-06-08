package com.gap.atpractice.botstyletest;

import com.google.common.base.Function;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

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
    public void waitForPageTitle(final String title, int timeToWaitSecs){

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
    public WebElement waitForElementPresent(final By byElement, int timeoutInSeconds){
        return waitForElementPresent(driver.findElement(byElement), timeoutInSeconds);
    }


    /**
     * Waiting for the element to be present before using it
     * @param element Webelement
     * @param timeoutInSeconds timeout in seconds
     * @return WebElement
     */
    public WebElement waitForElementPresent(final WebElement element, int timeoutInSeconds){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutInSeconds);

        WebElement we= wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
        return we;
    }

    /**
     * Wait until an element is not longer present
     * @param timeoutInSeconds timeout in seconds
     * @param element element to validate that´s not present
     */
    public void waitForElementNotPresent(final By element, int timeoutInSeconds){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutInSeconds);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**
     * Type desired value, this method ensures that the element is present and cleans the field before sending the keys
     * @param element Desired web element
     * @param text Desired text
     */
    public void type(WebElement element, String text){
        waitForElementPresent(element, 60);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Type desired value, this method ensures that the element is present and cleans the field before sending the keys
     * @param locator Desired locator type by
     * @param text Desired text
     */
    public void type(By locator, String text){
        WebElement element = waitForElementPresent(locator, 60);
        element.clear();
        element.sendKeys(text);
    }


    /**
     * Type desired value, this method ensures that the element is present and send the keys
     * @param element Desired web element
     * @param text Desired text
     */
    public void typeWithoutClearing(WebElement element, String text){
        waitForElementPresent(element, 60);
        element.sendKeys(text);
    }

    /**
     * Type desired value, this method ensures that the element is present and send the keys
     * @param locator Desired locator type by
     * @param text Desired text
     */
    public void typeWithoutClearing(By locator, String text){
        WebElement element = waitForElementPresent(locator, 60);
        element.sendKeys(text);
    }

    /**
     * Select an item from a list
     * @param element List element
     * @param value value to be selected
     */
    public void selectListElementByValue(WebElement element, String value){
        waitForElementPresent(element, 60);
        new Select(element).selectByValue(value);
    }

    /**
     * Select an item from a list
     * @param element List element
     * @param value value to be selected
     */
    public void selectListElementByValue(By element, String value){
        selectListElementByValue(driver.findElement(element), value);
    }

    /**
     * Click an element
     * @param element Web element to perform operation
     */
    public void clickElement(By element){
        waitForElementPresent(element, 60);
        driver.findElement(element).click();
    }

    /**
     * Click an element using Actions class
     * @param element WebElement to be clicked
     */
    public void clickElementActions(WebElement element){
        Actions act = new Actions(driver);
        act.click(element).build().perform();
    }

    /**
     * Click an element using Actions class
     * @param element By element to be clicked
     */
    public void clickElementActions(By element){
        clickElementActions(driver.findElement(element));
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
            waitForElementPresent(element, 60);
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
            waitForElementPresent(element, 60);
            driver.findElement(element).isDisplayed();
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
