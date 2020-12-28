package com.keke84.testng.multiThread;

import org.testng.annotations.Test;



public class MultiThreadOnAnnotation {
    //调用10次，线程数3
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1);
        long t_id = Thread.currentThread().getId();
        System.out.printf("Thread Id: %s%n",t_id);
    }
}
