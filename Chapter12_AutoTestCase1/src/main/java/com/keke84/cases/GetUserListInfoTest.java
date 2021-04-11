package com.keke84.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keke84.config.TestConfig;
import com.keke84.mapper.UserMapper;
import com.keke84.model.GetUserListCase;
import com.keke84.model.User;
import com.keke84.utils.DatabaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.util.List;

public class GetUserListInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户列表")
    public void testGetUserListInfo(){
        System.out.println("---------------------testGetUserListInfo------------------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        GetUserListCase getUserListCaseById = mapper.findGetUserListCaseById(1);
        System.out.println(getUserListCaseById);
        //获取调用接口得到的实际值
        List<User> userList_actual = getResult(getUserListCaseById);
        //断言
        getUserListCaseById.getExpected();

        //获取预期值
        List<User> userList_expect = mapper.getUserList(getUserListCaseById);


        //先判断长度是否一致
        Assert.assertEquals(userList_actual.size(),userList_expect.size());

        //通过fastJson转为JSONArray
        JSONArray actual_array = JSONArray.parseArray(JSON.toJSONString(userList_actual));
        JSONArray expect_array = JSONArray.parseArray(JSON.toJSONString(userList_expect));

        for (int i = 0; i < expect_array.size(); i++) {
            JSONObject actual = (JSONObject) actual_array.get(i);
            JSONObject expect = (JSONObject) expect_array.get(i);
            System.out.println("对比:");

            String actual_s = actual.toString();
            String expect_s = expect.toString();

            System.out.println("actual_s:"+actual_s);
            System.out.println("expect_s:"+expect_s);

            Assert.assertEquals(expect_s,actual_s);
        }
    }

    private boolean isNumber(String num){
        if (num.equals("1") || num.equals("0")){
            return true;
        }else {
            return false;
        }
    }

    private List<User> getResult(GetUserListCase getUserListCaseById) {

        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        post.setHeader("Content-Type","application/json");
        JSONObject getUserListObject = new JSONObject();
        getUserListObject.put("userName",getUserListCaseById.getUserName());
        getUserListObject.put("age",getUserListCaseById.getAge());
        //是字符串1、0，转为数字
        getUserListObject.put("sex",isNumber(getUserListCaseById.getSex())?Integer.parseInt(getUserListCaseById.getSex()):getUserListCaseById.getSex());
        StringEntity entity = new StringEntity(getUserListObject.toString(), ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result = "";

        try {
            CloseableHttpResponse execute = TestConfig.defaultHttpClient.execute(post);

            result = EntityUtils.toString(execute.getEntity(),"utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<User> userList = JSONArray.parseArray(result, User.class);
        return userList;

    }

}
