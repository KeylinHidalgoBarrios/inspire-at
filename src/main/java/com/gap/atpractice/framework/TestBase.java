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
    private String testLinkBuildName;

    /**
     * Setting up TestLink configuration
     * @param key TestLink key
     * @param url TestLink API URL
     * @param testCaseVersion Test case version
     * @param testCasePlatformId Test case platform
     * @param testCaseUrgency Test case urgency
     * @param testLinkProjectName Project name
     * @param testLinkPlanName Plan name
     * @param testPlanNotes Plan notes
     * @param testPlanActive Plan isActive
     * @param testPlanPublic Plan isPublic
     * @param testLinkBuildName Build name
     * @param testLinkBuildNotes Build notes
     * @param createPlanFlag Should a new plan be created
     * @param createBuildFlag Should a new build be created
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"testLinkKey", "testLinkUrl", "testLinkTestCasesVersion", "testLinkTestCasePlatformId", "testLinkTestCaseUrgency",
            "testLinkProjectName", "testLinkPlanName", "testPlanNotes", "testPlanActive", "testPlanPublic",
            "testLinkBuildName", "testLinkBuildNotes",  "createPlanFlag", "createBuildFlag"})
    public void testLinkSetup(String key, String url, String testCaseVersion,
                              String testCasePlatformId, String testCaseUrgency, String testLinkProjectName, String testLinkPlanName,
                              String testPlanNotes, String testPlanActive, String testPlanPublic,
                              String testLinkBuildName, String testLinkBuildNotes,
                              String createPlanFlag, String createBuildFlag){
        this.testLinkKey = key;
        this.testLinkUrl = url;
        this.testLinkTestCaseVersion = Integer.parseInt(testCaseVersion);
        this.testLinkTestCasePlatformId = Integer.parseInt(testCasePlatformId);
        this.testLinkTestCaseUrgency = Integer.parseInt(testCaseUrgency);
        this.testLinkProjectName = testLinkProjectName;
        this.testLinkPlanName = testLinkPlanName;
        this.testLinkBuildName = testLinkBuildName;

        createTestLinkPlanBuild(testPlanNotes, Boolean.parseBoolean(testPlanActive), Boolean.parseBoolean(testPlanPublic),
                testLinkBuildName, testLinkBuildNotes, createPlanFlag, createBuildFlag);
    }

    /**
     * Whether a new plan and build should be created
     * @param testPlanNotes Plan notes
     * @param testPlanActive Plan isActive
     * @param testPlanPublic Plan isPublic
     * @param testLinkBuildName Build name
     * @param testLinkBuildNotes Build notes
     * @param createPlanFlag Should a new plan be created
     * @param createBuildFlag Should a new build be created
     */
    public void createTestLinkPlanBuild(String testPlanNotes, Boolean testPlanActive, Boolean testPlanPublic,
                                        String testLinkBuildName, String testLinkBuildNotes, String createPlanFlag, String createBuildFlag){
        try {
            //TestLink connection
            TestLinkManager testLinkManager = new TestLinkManager(this.getTestLinkUrl(), this.getTestLinkKey());

            //-1 flag to check if it´s a valid plan Id
            Integer planId = -1;

            //If a new plan should be created
            if(Boolean.parseBoolean(createPlanFlag)) {
                //Check if plan already exists, in case flag is true
                if (!testLinkManager.isTestPlanAlreadyCreated(testLinkManager.getTestProjectByName(this.getTestLinkProjectName()).getId(), this.getTestLinkPlanName())) {
                    //If plan does not exists, is created
                    TestPlan testPlan = testLinkManager.createTestPlan(this.getTestLinkPlanName(), this.getTestLinkProjectName(), testPlanNotes,
                            testPlanActive, testPlanPublic);

                    //Saving plan id in case build should be created
                    planId = testPlan.getId();
                }
            }

            //If a new build should be created
            if(Boolean.parseBoolean(createBuildFlag)){
                //In case plan already existed id is -1 so now go get the plan id
                if(planId == -1) {
                    planId = testLinkManager.getTestPlanIdByPlanName(testLinkManager.getTestProjectByName(this.getTestLinkProjectName()).getId(), this.getTestLinkPlanName());
                }
                //Create build
                testLinkManager.createTestBuild(planId, testLinkBuildName, testLinkBuildNotes);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
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

    public String getTestLinkBuildName() {
        return testLinkBuildName;
    }
}
