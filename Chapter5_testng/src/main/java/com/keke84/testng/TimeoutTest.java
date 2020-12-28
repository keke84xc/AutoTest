package com.keke84.testng;

import org.testng.annotations.Test;

public class TimeoutTest {
    //单位ms，3秒内执行完，否则抛出异常
    @Test(timeOut = 3000)
    public void testSuccess(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("超时异常");
        }
    }
}
