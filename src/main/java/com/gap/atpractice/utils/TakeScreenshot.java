package com.gap.atpractice.utils;

import com.gap.atpractice.framework.SeleniumBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by keyhi on 4/24/2017.
 */
public class TakeScreenshot extends SeleniumBase{

    /**
     * Static method to take a screenshot from the UI in any important point
     * @param path path where the screenshot will be saved
     */
    public void takeScreenshot(WebDriver driver, String path, String fileType){
        //Taking the screenshot
        File fileToSave = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            //Saving the screenshot in the path
            FileUtils.copyFile(fileToSave, new File(path));
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
