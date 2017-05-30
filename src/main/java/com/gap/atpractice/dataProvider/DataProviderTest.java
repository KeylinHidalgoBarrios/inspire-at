package com.gap.atpractice.dataProvider;

import com.gap.atpractice.utils.JsonUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Created by keyhi on 5/29/2017.
 */
public class DataProviderTest {

    /**
     * Test login functionality using a set of values established within the method
     * @param method method calling the test case
     * @return 2D array with data needed to run the test
     */
    @DataProvider(name = "dpTestLocal")
    public static Object [][] dpLoginLocal(Method method){
        System.out.println(String.format("Data Provider name: %s", method.getName()));

        return new Object[][] {
                {"at_java_training@wearegap.com", "123queso"},
                {"fail1", "555"},
                {"fail2222", "666"}};
    }

    /**
     * Test login functionality using a set of values established in a JSON file
     * @param method method calling the test case
     * @return 2D array with data needed to run the test
     */
    @DataProvider(name = "dpTestJson")
    public static Object [][] dpLoginJson(Method method){
        System.out.println(String.format("Data Provider name: %s", method.getName()));

        //Using utils method to read from JSON file
        Object[][] result = new JsonUtils().readJsonFile();

        return result;
    }
}
