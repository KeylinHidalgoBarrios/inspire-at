package com.gap.atpractice.dataProvider;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Created by keyhi on 5/29/2017.
 */
public class DataProviderTest {

    @DataProvider(name = "dpTest001")
    public static Object [][] dpLogin(Method method){
        System.out.println(String.format("Data Provider name: %s", method.getName()));

        return new Object[][] {
                {"at_java_training@wearegap.com", "123queso"},
                {"fail1", "555"},
                {"fail2222", "666"}
        };
    }
}
