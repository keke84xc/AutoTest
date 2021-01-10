package com.keke84.httpclient.cookies;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import java.io.IOException;
import java.util.ResourceBundle;

public class MyCookiesForPostByFastJson {
    private HttpClient httpClient;
    private CookieStore cookieStore;
    private String getCookieUrl;
    private String postUrl1;
    @BeforeMethod
    public void beforeMethod(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        String host = resourceBundle.getString("test.host");
        String get_cookie_uri = resourceBundle.getString("getCookies.uri");
        getCookieUrl = host+get_cookie_uri;
        System.out.println("getCookieUrl"+getCookieUrl);

        String post_with_cookies = resourceBundle.getString("post.with.cookies.uri");
        postUrl1 = host+post_with_cookies;
        System.out.println("postUrl1"+postUrl1);
        //创建httpClient和cookieStore对象
        cookieStore = new BasicCookieStore();
        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }

    @Test
    public void getCookies(){
        HttpGet get = new HttpGet(getCookieUrl);
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity, "utf-8");
            System.out.println(body);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void testPostByJsonBodyAndCookies1(){

        HttpPost post = new HttpPost(postUrl1);
        String jsonBody = "{\"name\":\"huhansan\",\"age\":\"18\"}";
        //请求body
        StringEntity entity = new StringEntity(jsonBody,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("token","abc");
        post.setHeader("username","zhangsan");

        try {

            HttpResponse response = httpClient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity entity1 = response.getEntity();
            String result_body = EntityUtils.toString(entity1);
            System.out.println(result_body);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //使用fastJson
    @Test(dependsOnMethods = {"getCookies"})
    public void testPostByJsonBodyAndCookies3(){
        HttpPost post = new HttpPost(postUrl1);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name","huhansan");
        jsonObj.put("age","18");
        //设置请求头信息
        post.setHeader("token","abc");
        post.setHeader("username","zhangsan");
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(jsonObj.toString(), ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity1 = response.getEntity();
            String response_body = EntityUtils.toString(entity1);
            System.out.println(response_body);
            //将response结果转为JsonObject对象
            JSONObject jsonObject = JSONObject.parseObject(response_body);
            String code = (String) jsonObject.get("code");
            String msg = (String) jsonObject.get("msg");
            Assert.assertEquals(code,"1");
            Assert.assertEquals(msg,"成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    //使用jsonModel
//    @Test(dependsOnMethods = {"getCookies"})
//    public void testPostByJsonBodyAndCookies2(){
//        HttpPost post = new HttpPost(postUrl1);
//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("name","huhansan");
//        jsonObj.put("age","18");
//        //设置请求头信息
//        post.setHeader("token","abc");
//        post.setHeader("username","zhangsan");
//        post.setHeader("Content-Type","application/json");
//
//        StringEntity entity = new StringEntity(jsonObj.toString(),"UTF-8");
//        post.setEntity(entity);
//
//        try {
//            HttpResponse response = httpClient.execute(post);
//            HttpEntity entity1 = response.getEntity();
//            String response_body = EntityUtils.toString(entity1);
//            //将response结果转为JsonObject对象
//            //JSONObject responseJsonObj = new JSONObject(response_body);
//            JSONObject responseJsonObj = JSONObject.parseObject(response_body);
//            String code = (String) responseJsonObj.get("code");
//            String msg = (String) responseJsonObj.get("msg");
//
//            Assert.assertEquals(code,"1");
//            Assert.assertEquals(msg,"成功");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


}
