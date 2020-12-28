package com.keke84.testng.suite;

import org.testng.annotations.*;

public class PayTest {

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass...PayTest");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("afterClass...PayTest");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod...testPaySuccess");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod...testPaySuccess");
    }

    @Test
    public void testPaySuccess(){
        System.out.println("支付宝支付成功");
    }
}
