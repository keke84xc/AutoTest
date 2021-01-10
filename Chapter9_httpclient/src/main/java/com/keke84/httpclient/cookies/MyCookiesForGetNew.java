package com.keke84.httpclient.cookies;

import com.keke84.httpclient.utils.StringEncodingUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class MyCookiesForGetNew {

    private ResourceBundle bundle;
    private String url;
    private CloseableHttpClient httpClient;
    private static CookieStore cookieStore;
    private String urlWithCookie;

    @BeforeMethod
    public void beforeMethod(){
        //注意字符编码
        bundle = ResourceBundle.getBundle("application");
        String name_chinese = bundle.getString("test.chinese.name");
        try {
            byte[] bytes = name_chinese.getBytes("ISO-8859-1");
            String s = new String(bytes,"UTF-8");
            System.out.println(s);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String host = (String) bundle.getObject("test.host");
        String uri = bundle.getString("getCookies.uri");

        url = host+uri;
        cookieStore = new BasicCookieStore();
        //新版本设置cookie
        httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
        urlWithCookie = host+bundle.getString("get.with.cookies.uri");

    }

    @AfterMethod
    public void afterTest(){
        /*ClassLoader classLoader = MyCookiesForGetNew.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("test3.properties");
        Properties prop = new Properties();
        try {
            prop.load(resourceAsStream);
            String property = prop.getProperty("test.chinese.name");
            String encoding = StringEncodingUtils.getEncoding(property);
            String utf8_str = new String(property.getBytes(encoding),"UTF-8");
            //ISO-8859-1
            System.out.println(encoding);
            //测试222中文
            System.out.println(utf8_str);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Test
    public void testGetCookies(){
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
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
        //新建1个HttpClient对象，cookie使用上次获取的cookie
        //CloseableHttpClient httpClient2 = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

        try {
            //HttpResponse response = httpClient2.execute(httpGet);
            HttpResponse response = httpClient.execute(httpGet);
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
