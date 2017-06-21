package com.gap.atpractice.testsuites;

import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import com.gap.atpractice.framework.TestBase;
import com.gap.atpractice.framework.TestLinkManager;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by keyhi on 6/15/2017.
 */
@Test(groups = "testLink")
public class TestLinkManagerTest extends TestBase{

    /**
     * Create a test plan and a build
     * @param testPlanParameters
     * @param buildParameters
     */
    @Test(groups = "testLink")
    @Parameters({"testPlanParameters", "buildParameters"})
    public void testLinkManagement(String testPlanParameters, String buildParameters){
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
