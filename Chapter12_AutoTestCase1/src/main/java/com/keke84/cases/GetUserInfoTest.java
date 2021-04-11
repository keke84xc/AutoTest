package com.keke84.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keke84.config.TestConfig;
import com.keke84.mapper.UserMapper;
import com.keke84.model.GetUserInfoCase;
import com.keke84.model.User;
import com.keke84.utils.ConfigFile;
import com.keke84.utils.DatabaseUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取userId为1的用户信息")
    public void testGetUserInfo(){
        System.out.println("-------------------testGetUserInfo-----------------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        GetUserInfoCase getUserInfoCaseById = mapper.findGetUserInfoCaseById(1);
        System.out.println(getUserInfoCaseById);
        List<User> user = getUserInfoResult(getUserInfoCaseById);

        //通过调用接口，拿到实际值
        JSONArray actual_user = JSONArray.parseArray(JSON.toJSONString(user));
        //System.out.println(actual_user);
        //自己通过mybatis，从数据库拿到预期值
        List<User> userByGetUserInfoCase = mapper.getUserByGetUserInfoCase(getUserInfoCaseById);
//        System.out.println(userByGetUserInfoCase);
        JSONArray expect_user = JSONArray.parseArray(JSON.toJSONString(userByGetUserInfoCase));
        Assert.assertEquals(actual_user.size(),expect_user.size());

        for (int i = 0;i<expect_user.size();i++){

            JSONObject actual = (JSONObject) actual_user.get(i);
            JSONObject expect = (JSONObject) expect_user.get(i);
            System.out.println("对比:");
            String actual_s = actual.toString();
            String expect_s = expect.toString();
            System.out.println("actual_s:"+actual_s);
            System.out.println("expect_s:"+expect_s);
            Assert.assertEquals(expect_s,actual_s);

        }
    }

    private List<User> getUserInfoResult(GetUserInfoCase getUserInfoCaseById) {

        String getUserListUrl = TestConfig.getUserListUrl;
        System.out.println(getUserListUrl);
        HttpPost post = new HttpPost(getUserListUrl);
        post.setHeader("Content-Type","application/json");
        JSONObject jsonObject = new JSONObject();
        if (getUserInfoCaseById == null){
            System.out.println("user的id为空串，查询所有");
            jsonObject.put("id","");
        }else {
            jsonObject.put("id",getUserInfoCaseById.getUserId());
        }
        StringEntity entity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String res = "";
        try {
            CloseableHttpResponse execute = TestConfig.defaultHttpClient.execute(post);

            HttpEntity entity1 = execute.getEntity();

            res = EntityUtils.toString(entity1, "utf-8");

            //System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<User> userList = JSONArray.parseArray(res, User.class);

        return userList;

    }
}
