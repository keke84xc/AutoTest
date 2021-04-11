package com.keke84.cases;

import com.keke84.config.TestConfig;
import com.keke84.mapper.UserMapper;
import com.keke84.model.AddUserCase;
import com.keke84.model.InterfaceName;
import com.keke84.utils.ConfigFile;
import com.keke84.utils.DatabaseUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
//    private HttpClient httpClient;
//    private CookieStore cookieStore;
//    private String loginUrl;
//    @BeforeTest
//    public void beforeTest(){
//
//        cookieStore = new BasicCookieStore();
//        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//        loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
//
//    }


    //@Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    @Test
    public void testAddUser(){

        System.out.println("---------------testAddUser------------");

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //1表示：成功添加user的case数据
        AddUserCase addUserCaseById = mapper.findAddUserCaseById(1);
        System.out.println(addUserCaseById);


//        //http://localhost:8098/v1/login
//        System.out.println(loginUrl);
//
//        HttpPost post = new HttpPost(loginUrl);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userName","zhangsan");
//        jsonObject.put("password","abc123");
//        post.setHeader("Content-Type","application/json");
//        StringEntity entity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
//        post.setEntity(entity);
//
//        try {
//            HttpResponse response = httpClient.execute(post);
//            HttpEntity entity1 = response.getEntity();
//            String response_body = EntityUtils.toString(entity1);
//            System.out.println(response_body);
//            //System.out.println(cookieStore.getCookies());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String reslut = getResult(addUserCaseById);
        System.out.println(reslut);
        Assert.assertEquals(reslut,addUserCaseById.getExpected());

    }

    //sex、permission、isDelete如果是字符串1、0，则转为int
    private boolean isNumber(String num){
        if (num.equals("1") || num.equals("0")){
            return true;
        }else {
            return false;
        }
    }

    private String getResult(AddUserCase addUserCaseById) {

        String addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        System.out.println("添加用户接口："+addUserUrl);
        HttpPost addUserPost = new HttpPost(addUserUrl);

        JSONObject addUserBody = new JSONObject();
        addUserBody.put("userName",addUserCaseById.getUserName());
        addUserBody.put("password",addUserCaseById.getPassword());
        addUserBody.put("age",addUserCaseById.getAge());

        addUserBody.put("sex",isNumber(addUserCaseById.getSex())?Integer.parseInt(addUserCaseById.getSex()):addUserCaseById.getSex());
        addUserBody.put("permission",isNumber(addUserCaseById.getPermission())?Integer.parseInt(addUserCaseById.getPermission()):addUserCaseById.getPermission());
        addUserBody.put("isDelete",isNumber(addUserCaseById.getIsDelete())?Integer.parseInt(addUserCaseById.getIsDelete()):addUserCaseById.getIsDelete());

        //设置请求头
        addUserPost.setHeader("Content-Type","application/json");


//        //设置cookie
//        TestConfig.httpClient = HttpClients.custom().setDefaultCookieStore(TestConfig.cookieStore).build();
//
//        TestConfig.httpClient.execute();

        //设置请求体
        StringEntity addUserEntity = new StringEntity(addUserBody.toString(), ContentType.APPLICATION_JSON);
        addUserPost.setEntity(addUserEntity);

        String result = "";
        try {

            TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
            HttpResponse addUserResponse = TestConfig.defaultHttpClient.execute(addUserPost);
            HttpEntity entity1 = addUserResponse.getEntity();
            result = EntityUtils.toString(entity1,"utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
