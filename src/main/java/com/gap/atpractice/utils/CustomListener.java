package com.gap.atpractice.utils;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.framework.TestLinkManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;

/**
 * Created by keyhi on 6/8/2017.
 */
public class CustomListener implements ITestListener{
    private final String RESULT_PASSED = "PASSED";
    private final String RESULT_FAILED = "FAILED";
    private final String RESULT_SKIPPED = "SKIPPED";
    private final String RESULT_NOT_RECOGNIZED = "RESULT CODE NOT RECOGNIZED";

    @Override
    public void onTestStart(ITestResult result) {
    }

    /**
     * When a test passes the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        testCaseManagementTestLink(result);

        printStatus(result);
    }

    /**
     * When a test fails the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestFailure(ITestResult result) {
        testCaseManagementTestLink(result);

        printStatus(result);
    }

    /**
     * When a test is skipped the execution, a Summary displays
     * @param result ITestResult obtained from test execution
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        testCaseManagementTestLink(result);

        printStatus(result);
        System.out.println(result.toString());
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
        switch (status){
            case ITestResult.SUCCESS:
                return RESULT_PASSED;
            case ITestResult.FAILURE:
                return RESULT_FAILED;
            case ITestResult.SKIP:
                return RESULT_SKIPPED;
            default:
                return RESULT_NOT_RECOGNIZED;
        }
    }

    /**
     * Manage test case in TestLink, this method adds the test case to the test plan and also update the execution result.
     * @param result result from test case execution
     */
    private void testCaseManagementTestLink(ITestResult result){
        try {
            //Get test case id from Testlink, this is obtained from the test parameters
            Object [] methodParameters = result.getParameters();
            Integer testCaseId = Integer.parseInt(methodParameters[methodParameters.length-1].toString());

            //Get TestBase class instance from test result, to obtain all needed variables and values
            TestBase testBase = (TestBase)(result.getInstance());

            //TestLinkManager class to call all the test link methods
            TestLinkManager testLinkManager = new TestLinkManager(testBase.getTestLinkUrl(), testBase.getTestLinkKey());

            //Get ids
            Integer testProjectId = testLinkManager.getTestProjectByName(testBase.getTestLinkProjectName()).getId();
            Integer testPlanId = testLinkManager.getTestPlanByName(testBase.getTestLinkPlanName(), testBase.getTestLinkProjectName()).getId();
            Integer buildId = testLinkManager.getTestBuildIdByBuildName(testPlanId, testBase.getTestLinkBuildName());

            //Adding all test cases to test link Plan
            if (!testLinkManager.isTestCaseAddedToTestPlan(testPlanId, buildId, testCaseId))
                addTestCaseToTestLinkPlan(testCaseId, testBase, testLinkManager, testProjectId, testPlanId);

            //Depending on the ITestResult variable status, set the ExecutionStatus result
            ExecutionStatus executionStatus = ExecutionStatus.PASSED;

            switch (getStatus(result.getStatus())) {
                case RESULT_FAILED:
                    executionStatus = ExecutionStatus.FAILED;

                    //Take screenshot in case execution status is Failed.
                    String testCaseExternalId = testLinkManager.getTestCaseExternalId(testCaseId);
                    new TakeScreenshot().takeScreenshot(testBase.getDriver(), "./src/main/resources/screenshots/".concat(testCaseExternalId).concat("_FAILED.png"), "png");

                    break;
                case RESULT_SKIPPED:
                    executionStatus = ExecutionStatus.NOT_RUN;
                    break;
                case RESULT_NOT_RECOGNIZED:
                    executionStatus = ExecutionStatus.BLOCKED;
                    break;
            }

            //Update the test run with the needed status
            updateTestRunStatus(testCaseId, testLinkManager, executionStatus, testPlanId, buildId);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add test cases to a TestLink Plan
     * @param testCaseId Test case id
     * @param testBase TestBase class instance obtaind from ITestResult
     * @param testLinkManager TestLinkManager class instance
     * @param testProjectId Project id
     * @param testPlanId Plan id
     */
    private void addTestCaseToTestLinkPlan(Integer testCaseId, TestBase testBase, TestLinkManager testLinkManager,
                                           Integer testProjectId, Integer testPlanId){
        testLinkManager.addTestCasesToTestPlan(testCaseId, testProjectId, testPlanId, testBase.getTestLinkTestCaseVersion(),
                testBase.getTestLinkTestCasePlatformId(), testBase.getTestLinkTestCaseUrgency());
    }

    /**
     * Update test runs from a plan to set execution as Passed, Failed, Not Run
     * @param testCaseId Test case id
     * @param testLinkManager TestLinkManager class instance
     * @param status Execution Status predefined code depending on the execution status
     * @param testPlanId test plan id
     * @param buildId build id
     */
    private void updateTestRunStatus(Integer testCaseId, TestLinkManager testLinkManager,
                                     ExecutionStatus status, Integer testPlanId, Integer buildId){
        testLinkManager.updateTestRunsStatus(testCaseId, null, testPlanId,
                status, buildId, null, "Key test run notes", true, null, null, null,
                null, true);
    }
}
