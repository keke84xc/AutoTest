package com.keke84.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ParameterByDataProviderTest {
    @DataProvider(name = "userInfo")
    public Object[][] userInfoDataProvider(){
        return new Object[][]{
                {"zhangsan",18},
                {"lisi",20}
        };
    }
    @Test(dataProvider = "userInfo")
    public void parameterTest1(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }

    @Test(dataProvider = "newData")
    public void parameterTest2(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }
    @Test(dataProvider = "newData")
    public void parameterTest3(String name,int age){
        System.out.println("name:"+name+",age:"+age);
    }

    @DataProvider(name = "newData")
    public Object[][] methodDataProvider(Method method){
        Object[][] params = null;
        if (method.getName().equals("parameterTest2")){
            params = new Object[][]{
                    {"aaa",18},
                    {"bbb",19}
            };
        }else if (method.getName().equals("parameterTest3")){
            params = new Object[][]{
                    {"ccc", 28},
                    {"ddd", 29}
            };
        }
        return params;
    }




}


