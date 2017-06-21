package com.gap.atpractice.testngfactory;

import com.gap.atpractice.testsuites.HomeTest;
import org.testng.annotations.Factory;

/**
 * Created by keyhi on 6/1/2017.
 */
public class HomeFactory {

    /**
     * Using Factory to run HomeTest tests multiple times with a single execution
     * @return Factory will automatically execute the content of Object[]
     */
    @Factory
    public Object[] TestngFactory(){
        //Object Array that will contain the instance of the class that will run multiple times
        Object[] result = new Object[3];

        for(int i = 0; i < 3; i++){
            result[i] = new HomeTest();
        }

        return result;
    }
}
