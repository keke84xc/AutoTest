package com.keke84.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsTest {

    @Test(groups = {"server"})
    public void test1(){
        System.out.println("server组的test1");
    }
    @Test(groups = {"server"})
    public void test2(){
        System.out.println("server组的test2");
    }
    @Test(groups = {"client"})
    public void test3(){
        System.out.println("client组的test3");
    }
    @Test(groups = {"client"})
    public void test4(){
        System.out.println("client组的test4");
    }
    @BeforeGroups({"server"})
    public void beforeGroups(){
        System.out.println("这是server组里的所有测试方法运行之前运行的方法");
    }
    @AfterGroups({"server"})
    public void afterGroups(){
        System.out.println("这是server组里的所有测试方法运行之后运行的方法");
    }
}
