package com.keke84.testng;

import org.testng.annotations.Test;

public class ExpectExceptionTest {

    //期望抛出RuntimeException
    @Test(expectedExceptions = {RuntimeException.class})
    public void testRunTimeException(){
        System.out.println("这是一个失败的异常测试");
        //实际抛出
        throw new RuntimeException("aa");
    }
}
