package com.gap.atpractice.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by keyhi on 4/24/2017.
 */
public class TakeScreenshot {

    /**
     * Static method to take a screenshot from the UI in any important point
     * @param webDriver driver initialized
     * @param path path where the screenshot will be saved
     */
    public static void takeScreenshot(WebDriver webDriver, String path, String fileType){

        /*
             //Taking the screenshot
            File fileToSave = new File("");

            if(fileType.equals("file")){
                fileToSave = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            }
            else if(fileType.equals("base64")){
                fileToSave = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);
            }
         */
        //Taking the screenshot
        File fileToSave = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);

        try {
            //Saving the screenshot in the path
            FileUtils.copyFile(fileToSave, new File(path));
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
