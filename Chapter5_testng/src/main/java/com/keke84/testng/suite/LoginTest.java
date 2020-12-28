package com.keke84.testng.suite;

import org.testng.annotations.*;

public class LoginTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass...LoginTest");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass...LoginTest");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod...testLoginSuccess");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod...testLoginSuccess");
    }

    @Test
    public void testLoginSuccess(){
        System.out.println("taobao登录成功");
    }
}
