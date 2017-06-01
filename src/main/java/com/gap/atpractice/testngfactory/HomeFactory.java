package com.gap.atpractice.testngfactory;

import com.gap.atpractice.testsuites.HomeTest;
import org.testng.annotations.Factory;

/**
 * Created by keyhi on 6/1/2017.
 */
public class HomeFactory {
    @Factory
    public Object[] TestngFactory(){
        Object[] result = new Object[3];

        for(int i = 0; i < 3; i++){
            result[i] = new HomeTest();
        }

        return result;
    }
}
