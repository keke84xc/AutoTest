package com.keke84.testng.parameter;

import org.testng.annotations.Test;

import java.util.Random;

public class MyFactoryTest {

    private String username;
    private String password;
    private String cookie;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public MyFactoryTest(){

    }

    public MyFactoryTest(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Test
    public void loginTest(){
        System.out.println("通过："+username+","+password+"登录");

        //模拟登录成功后拿到不同的cookie
        Random rand = new Random();
        //生成[1,100]之间的随机数
        int aa = rand.nextInt(100)+1;
        this.cookie = "20190628"+aa;

    }

    @Test(dependsOnMethods = {"loginTest"})
    public void publishArticleTest(){

        //模拟登录成功后发布文章
        System.out.println("拿到了cookie："+this.cookie+",发表了文章");
    }

}
