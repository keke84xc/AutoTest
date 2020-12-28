package com.keke84.testng.groups;

import org.testng.annotations.Test;

@Test(groups = {"stu"})
public class StudentGroup2Test {
    public void stu1(){
        System.out.println("StudentGroup2Test中的stu1运行");
    }
    public void stu2(){
        System.out.println("StudentGroup2Test中的stu2运行");
    }
}
