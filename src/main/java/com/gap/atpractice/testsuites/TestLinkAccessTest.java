package com.gap.atpractice.testsuites;

import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.framework.TestLinkAccess;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by keyhi on 6/15/2017.
 */
@Test(groups = "testLink")
public class TestLinkAccessTest extends TestBase{

    @Test(groups = "testLink")
    @Parameters({"testPlanParameters", "buildParameters", "testCasesId", "testCasesVersion", "testCasePlatformId", "testCaseUrgency"})
    public void testLinkManagement(String testPlanParameters, String buildParameters, String testCasesId, String testCaseVersion,
                                   String testCasePlatformId, String testCaseUrgency){
        try {
            TestLinkAccess testLinkAccess = new TestLinkAccess(testLinkUrl, testLinkKey);

            String[] testPlanArray = testPlanParameters.split(",");
            TestPlan testPlan = testLinkAccess.createTestPlan(testPlanArray[0], testPlanArray[1], testPlanArray[2],
                    Boolean.parseBoolean(testPlanArray[3]), Boolean.parseBoolean(testPlanArray[4]));
/*
            String[] testCasesIdArray = testCasesId.split(",");
            testLinkAccess.addTestCasesToTestLinkPlan(testCasesIdArray, testLinkAccess.getTestProjectByName(testPlanArray[1]).getId(), testPlan.getId(), Integer.parseInt(testCaseVersion),
                    Integer.parseInt(testCasePlatformId), Integer.parseInt(testCaseUrgency));*/

            String[] buildArray = buildParameters.split(",");
            testLinkAccess.createTestLinkBuild(testPlan.getId(), buildArray[0], buildArray[1]);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
