package com.keke84.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class DataFactory {
    //dataProvider+factory组合方法1

    @DataProvider(name = "account")
    public Object[][] accountDataProvider(){
        return new Object[][]{
                //等同于：new Object[]{"zhangsan","111"},
                {"zhangsan","111"},
                {"lisi","222"},
                {"wangwu","333"}
        };
    }

    @Factory(dataProvider = "account")
    public Object[] instanceTestClass(String username,String pwd){
        Object[] account = new Object[]{
            new MyFactoryTest(username,pwd)
        };
//        Object[] account = new Object[]{};
//
//        account[0] = new MyFactoryTest(username,pwd);
        return account;
    }


//    @DataProvider(name = "account")
//    public Object[][] accountDataProvider2(){
//        return new Object[][]{
//                new Object[]{"zhangsan","111"},
//                new Object[]{"lisi","222"},
//                new Object[]{"wangwu","333"}
//        };
//    }
//
//    @Factory(dataProvider = "account")
//    public Object[] instanceTestClass(String username,String pwd){
//        Object[] account = new Object[]{
//                new MyFactoryTest(username,pwd)
//        };
//        return account;
//    }


}
