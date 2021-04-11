package com.keke84.cases;

import com.keke84.config.TestConfig;
import com.keke84.mapper.UserMapper;
import com.keke84.model.InterfaceName;
import com.keke84.model.LoginCase;
import com.keke84.model.User;
import com.keke84.utils.ConfigFile;
import com.keke84.utils.DatabaseUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备")
    public void beforeTest(){

        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }

    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void testLoginTrue(){

        System.out.println("-------------testLoginTrue--------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.findUserById(1);
        System.out.println(userById);
        LoginCase loginCaseById = mapper.findLoginCaseById(1);
        System.out.println(loginCaseById);
        DatabaseUtil.closeSession(sqlSession);

        //1、发送请求，获取返回
        String result = getResult(loginCaseById);
        //2、验证结果
        System.out.println("登录接口返回："+result);
        Assert.assertEquals(result,loginCaseById.getExpected());



    }



    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void testLoginFalse(){

        System.out.println("-------------testLoginFalse--------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        LoginCase loginCaseById = mapper.findLoginCaseById(2);
        System.out.println(loginCaseById);
        DatabaseUtil.closeSession(sqlSession);


    }


    private String getResult(LoginCase loginCaseById) {

        HttpPost post = new HttpPost(TestConfig.loginUrl);
        post.setHeader("Content-Type","application/json");
        JSONObject body = new JSONObject();
        body.put("userName",loginCaseById.getUserName());
        body.put("password",loginCaseById.getPassword());

        StringEntity entity = new StringEntity(body.toString(), ContentType.APPLICATION_JSON);

        post.setEntity(entity);

        String result = "";
        try {
            CloseableHttpResponse execute = TestConfig.defaultHttpClient.execute(post);
            HttpEntity entity1 = execute.getEntity();
            result = EntityUtils.toString(entity1, "utf-8");
            //重要：赋值cookieStore
            TestConfig.cookieStore = TestConfig.defaultHttpClient.getCookieStore();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }






}
