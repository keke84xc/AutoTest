package com.keke84.testng.groups;

import org.testng.annotations.Test;

@Test(groups = {"teacher"})
public class TeacherGroup1Test {
    public void teacher1(){
        System.out.println("TeacherGroup1Test的teacher1运行");
    }
    public void teacher2(){
        System.out.println("TeacherGroup1Test的teacher2运行");
    }
}
