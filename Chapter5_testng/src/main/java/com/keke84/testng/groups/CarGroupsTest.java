package com.keke84.testng.groups;

import org.testng.annotations.Test;

public class CarGroupsTest {

    @Test(groups = {"toyota"})
    public void testCamry(){
        System.out.println("toyota group...testCamry...run!!!");
    }
    @Test(groups = {"toyota"})
    public void testAvalon(){
        System.out.println("toyota group...testAvalon...run!!!");
    }
    @Test(groups = {"bmw"})
    public void testThe3(){
        System.out.println("bmw group...testThe3...run!!!");
    }
    @Test(groups = {"bmw"})
    public void testX5(){
        System.out.println("bmw group...testX5...run!!!");
    }
//    @Test(dependsOnGroups = {"toyota","bmw"})
//    public void testAllCars(){
//        System.out.println("testAllCars");
//    }
}
