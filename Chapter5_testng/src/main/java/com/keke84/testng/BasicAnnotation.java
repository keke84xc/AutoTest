package com.keke84.testng;
import org.testng.annotations.*;

public class BasicAnnotation {

    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod");
    }


    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass");
    }

    @BeforeSuite
    public void beforeSuit(){
        System.out.println("beforeSuit");
    }

    @AfterSuite
    public void afterSuit(){
        System.out.println("AfterSuite");
    }





}
