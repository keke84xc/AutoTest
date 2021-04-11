package com.keke84.cases;

import com.alibaba.fastjson.JSONObject;
import com.keke84.config.TestConfig;
import com.keke84.mapper.UserMapper;
import com.keke84.model.UpdateUserInfoCase;
import com.keke84.utils.ConfigFile;
import com.keke84.utils.DatabaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void testUpdateUserInfo(){
        System.out.println("--------------------testUpdateUserInfo------------------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UpdateUserInfoCase updateUserInfoCaseById = mapper.findUpdateUserInfoCaseById(1);
        System.out.println(updateUserInfoCaseById);
        String result = getUpdateUserResult(updateUserInfoCaseById);
        Assert.assertEquals(result,updateUserInfoCaseById.getExpected());
    }

    private String getUpdateUserResult(UpdateUserInfoCase updateUserInfoCaseById) {
        String updateUserUrl = TestConfig.updateUserInfoUrl;
        HttpPost post = new HttpPost(updateUserUrl);
        JSONObject object = new JSONObject();
        //注意，拿的是userId
        object.put("id",updateUserInfoCaseById.getUserId());
        object.put("userName",updateUserInfoCaseById.getUserName());
        object.put("sex",updateUserInfoCaseById.isSex());
        object.put("age",updateUserInfoCaseById.getAge());
        object.put("delete",updateUserInfoCaseById.isDelete());
        StringEntity entity = new StringEntity(object.toString(), ContentType.APPLICATION_JSON);
        post.setHeader("Content-Type","application/json");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result = "";
        try {
            CloseableHttpResponse execute = TestConfig.defaultHttpClient.execute(post);

            result = EntityUtils.toString(execute.getEntity(), "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public  void testDeleteUserInfo(){
        System.out.println("------------------testDeleteUserInfo---------------------");
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UpdateUserInfoCase updateUserInfoCaseById = mapper.findUpdateUserInfoCaseById(2);
        System.out.println(updateUserInfoCaseById);
        //Assert.fail("测试testDeleteUserInfo断言失败！");
        String result = getUpdateUserResult(updateUserInfoCaseById);
        Assert.assertEquals(result,updateUserInfoCaseById.getExpected());

    }

}
