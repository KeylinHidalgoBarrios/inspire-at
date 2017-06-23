package com.gap.atpractice.framework;

import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import org.testng.annotations.*;

import java.net.MalformedURLException;

/**
 * Created by keyhi on 5/25/2017.
 */
public class TestBase extends SeleniumBase {

    //TestLink parameters, these are set before suite and are global so Tests can access them anytime.
    private String testLinkKey;
    private String testLinkUrl;
    private Integer testLinkTestCaseVersion;
    private Integer testLinkTestCasePlatformId;
    private Integer testLinkTestCaseUrgency;
    private String testLinkProjectName;
    private String testLinkPlanName;
    private Integer testLinkTestBuildId;

    /**
     * Setting TestLink values to access them from tests classes
     * @param key TestLink key
     * @param url TestLink API URL
     * @param testCaseVersion test case version
     * @param testCasePlatformId test case platform
     * @param testCaseUrgency test case urgency
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkKey", "testLinkUrl", "testLinkTestCasesVersion", "testLinkTestCasePlatformId", "testLinkTestCaseUrgency",
            "testLinkProjectName", "testLinkPlanName", "testLinkTestBuildId", "testLinkTestBuildName"})
    public void setTestLinkAccessValues(String key, String url, String testCaseVersion,
                                        String testCasePlatformId, String testCaseUrgency, String testLinkProjectName, String testLinkPlanName,
                                        String testLinkTestBuildId, String testLinkTestBuildName){
        this.testLinkKey = key;
        this.testLinkUrl = url;
        this.testLinkTestCaseVersion = Integer.parseInt(testCaseVersion);
        this.testLinkTestCasePlatformId = Integer.parseInt(testCasePlatformId);
        this.testLinkTestCaseUrgency = Integer.parseInt(testCaseUrgency);
        this.testLinkProjectName = testLinkProjectName;
        this.testLinkPlanName = testLinkPlanName;
        this.testLinkTestBuildId = Integer.parseInt(testLinkTestBuildId);
    }

/*
    @BeforeSuite(alwaysRun = true)
    @Parameters({"testPlanParameters", "buildParameters", "createPlanBuild"})
*/
    public void testLinkManagement(String testPlanParameters, String buildParameters, String createPlanBuild){
        if(Boolean.getBoolean(createPlanBuild)){
            try {
                TestLinkManager testLinkManager = new TestLinkManager(this.getTestLinkUrl(), this.getTestLinkKey());

                String[] testPlanArray = testPlanParameters.split(",");
                TestPlan testPlan = testLinkManager.createTestPlan(this.getTestLinkPlanName(), this.getTestLinkProjectName(), testPlanArray[0],
                        Boolean.parseBoolean(testPlanArray[1]), Boolean.parseBoolean(testPlanArray[2]));

                String[] buildArray = buildParameters.split(",");
                testLinkManager.createTestBuild(testPlan.getId(), buildArray[0], buildArray[1]);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initializing Webdriver instance so every other class extending from this one has the instance
     * @param browser Browser to be used
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    protected void initSetup(String browser){
        setup(browser);
        this.setDriver (getDriver());
    }

    /**
     * Method to quit the browser instance
     */
    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        this.getDriver().quit();
    }

    /*Getter methods to access class private variables*/
    public String getTestLinkKey() {
        return testLinkKey;
    }

    public String getTestLinkUrl() {
        return testLinkUrl;
    }

    public Integer getTestLinkTestCaseVersion() {
        return testLinkTestCaseVersion;
    }

    public Integer getTestLinkTestCasePlatformId() {
        return testLinkTestCasePlatformId;
    }

    public Integer getTestLinkTestCaseUrgency() {
        return testLinkTestCaseUrgency;
    }

    public String getTestLinkProjectName() {
        return testLinkProjectName;
    }

    public String getTestLinkPlanName() {
        return testLinkPlanName;
    }

    public Integer getTestLinkTestBuildId() {
        return testLinkTestBuildId;
    }
}
