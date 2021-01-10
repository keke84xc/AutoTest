package com.keke84.httpclient.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyCookiesForGetOld {

    private ResourceBundle bundle;
    private String url;
    private static CookieStore cookieStore;
    private DefaultHttpClient httpClient2;
    private String urlWithCookie;

    @BeforeMethod
    public void beforeMethod(){
        //注意字符编码
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        String host = (String) bundle.getObject("test.host");
        String uri = bundle.getString("getCookies.uri");
        url = host+uri;
        //旧版本设置cookie
        httpClient2 = new DefaultHttpClient();
        //旧版本设置cookieStore
        cookieStore = httpClient2.getCookieStore();
        urlWithCookie = host+bundle.getString("get.with.cookies.uri");

    }


    @Test
    public void testGetCookies(){

        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse response = httpClient2.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity,"utf-8");
            System.out.println(body);
            //从cookieStore获取所有的cookie
            List<Cookie> cookies = cookieStore.getCookies();
            for (Cookie cookie:cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name+":"+value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookie(){

        HttpGet httpGet = new HttpGet(urlWithCookie);
        //新建1个DefaultHttpClient对象，并设置cookieStore
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.setCookieStore(cookieStore);
        try {
            HttpResponse response = defaultHttpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            //响应状态码：200
            System.out.println("响应状态码："+statusCode);
            HttpEntity entity = response.getEntity();
            String sBody = EntityUtils.toString(entity, "utf-8");
            System.out.println(sBody);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
