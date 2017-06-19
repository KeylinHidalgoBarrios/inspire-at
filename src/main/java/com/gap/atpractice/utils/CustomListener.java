package com.gap.atpractice.utils;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.framework.TestLinkManagement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;

/**
 * Created by keyhi on 6/8/2017.
 */
public class CustomListener extends TestBase implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
    }

    /**
     * When a test passes the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        printStatus(result);

        testCaseManagement(result);

    }

    /**
     * When a test fails the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestFailure(ITestResult result) {
        printStatus(result);
        new TakeScreenshot().takeScreenshot(((TestBase)result.getInstance()).getDriver(), "./src/main/resources/screenshots/".concat(result.getName()).concat("FAILED.png"), "png");
        System.out.println(result.toString());
        testCaseManagement(result);

    }

    /**
     * When a test is skipped the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        printStatus(result);
        System.out.println(result.toString());
        testCaseManagement(result);

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
        Long runningDuration = result.getEndMillis()-result.getStartMillis();

        StringBuilder message = new StringBuilder();
        message.append("*****************************************************\n");
        message.append("SUMMARY\n");
        message.append("*****************************************************\n");
        message.append("|-- Test Class: ".concat(result.getInstanceName().concat("\n")));
        message.append("|-- Method: ".concat(result.getName()).concat("\n"));
        message.append("|-- Execution Time: ".concat(runningDuration.toString()).concat(" ms\n"));
        message.append("|-- Status: ".concat(getStatus(result.getStatus())).concat("\n"));
        message.append("*****************************************************\n\n\n");

        System.out.println(message);
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

    /**
     * Manage test case in TestLink.
     * @param result result from test case execution
     */
    private void testCaseManagement(ITestResult result){
        try {
            Object [] methodParameters = result.getParameters();
            Integer tCExternalId = Integer.parseInt(methodParameters[methodParameters.length-2].toString());
            Integer tCId = Integer.parseInt(methodParameters[methodParameters.length-1].toString());

            TestBase testBase = (TestBase)(result.getInstance());

            TestLinkManagement testLinkManagement = new TestLinkManagement(testBase.getTestLinkUrl(), testBase.getTestLinkKey());

            Boolean testCaseAdded  = addTestCaseToTestLink(tCExternalId, testBase, testLinkManagement);

            if(testCaseAdded){
                updateTestRunStatus(tCId, tCExternalId, testBase, testLinkManagement, ExecutionStatus.PASSED);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Boolean addTestCaseToTestLink(Integer testCaseId, TestBase testBase, TestLinkManagement testLinkManagement){
        int status = testLinkManagement.addTestCasesToTestLinkPlan(testCaseId, testLinkManagement.getTestProjectByName(testBase.getTestLinkProjectName()).getId(),
                testLinkManagement.getTestPlanByName(testBase.getTestLinkPlanName(), testBase.getTestLinkProjectName()).getId(), testBase.getTestLinkTestCaseVersion(),
                testBase.getTestLinkTestCasePlatformId(), testBase.getTestLinkTestCaseUrgency());

        return status == 2264 ? true:false;
    }

    private void updateTestRunStatus(Integer testCaseId, Integer testCaseExternalId, TestBase testBase, TestLinkManagement testLinkManagement,
                                     ExecutionStatus status){
        /*testLinkManagement.updateTestRunsStatus(testCaseId, testCaseExternalId, testLinkManagement.getTestPlanByName(testBase.getTestLinkPlanName(), testBase.getTestLinkProjectName()).getId(),
                status, );*/
    }
}
