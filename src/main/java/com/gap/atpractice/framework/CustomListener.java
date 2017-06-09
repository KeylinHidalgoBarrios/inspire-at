package com.gap.atpractice.framework;

import com.gap.atpractice.testsuites.TestBase;
import com.gap.atpractice.utils.TakeScreenshot;
import org.apache.bcel.generic.RETURN;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by keyhi on 6/8/2017.
 */
public class CustomListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
    }

    /**
     * When a test passes the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Current method: ".concat(result.getName()));
        printStatus(result);
    }

    /**
     * When a test fails the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Current method: ".concat(result.getName()));
        printStatus(result);
        new TakeScreenshot().takeScreenshot(((TestBase)result.getInstance()).getDriver(), "./src/main/resources/screenshots/".concat(result.getName()).concat("FAILED.png"), "png");
    }

    /**
     * When a test is skipped the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Current method: ".concat(result.getName()));
        printStatus(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    /**
     * Print in console test execution status
     * @param result ITestResult after test execution
     */
    private void printStatus(ITestResult result){
        System.out.println("*****************************************************");
        System.out.println("SUMMARY");
        System.out.println("*****************************************************");
        System.out.println("|-- Test Class: ".concat(result.getInstanceName()));
        System.out.println("|-- Method: ".concat(result.getName()));
        Long runningDuration = result.getEndMillis()-result.getStartMillis();
        System.out.println("|-- Execution Time: ".concat(runningDuration.toString()));
        System.out.println("|-- Status: ".concat(getStatus(result.getStatus())));
        System.out.println("*****************************************************");
        System.out.println("");
        System.out.println("");
    }

    /**
     * Get status name according to status code
     * @param status Status code obtained from ITestResult
     * @return status name
     */
    private String getStatus(int status){
        String result;
        switch (status){
            case ITestResult.SUCCESS:
                return "PASSED";
            case ITestResult.FAILURE:
                return "FAILED";
            case ITestResult.SKIP:
                return "SKIPPED";
            default:
                return "RESULT CODE NOT RECOGNIZED";
        }
    }
}
