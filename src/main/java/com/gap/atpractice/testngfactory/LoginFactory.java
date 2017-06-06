package com.gap.atpractice.testngfactory;

import com.gap.atpractice.testsuites.HomeTest;
import com.gap.atpractice.testsuites.Login.LoginTest;
import org.testng.annotations.Factory;

/**
 * Created by keyhi on 6/1/2017.
 */
public class LoginFactory {

    /**
     * Using Factory to run HomeTest tests multiple times with a single execution
     * @return Factory will automatically execute the content of Object[]
     */
    @Factory
    public Object[] TestngFactory(){
        Object[] result = new Object[2];

        result[0] = new LoginTest();
        result[1] = new HomeTest();

        return result;
    }
}
