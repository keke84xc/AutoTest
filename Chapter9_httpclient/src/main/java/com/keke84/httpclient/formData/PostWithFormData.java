package com.keke84.httpclient.formData;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PostWithFormData {
    private String url = "http://op.juhe.cn/onebox/football/team";
    private HttpClient httpClient;
    @BeforeMethod
    public void beforeMethod(){
        httpClient = HttpClients.createDefault();
    }
    @Test
    public void testGetBayern1(){
        HttpPost post = new HttpPost(url);
        //form表单请求，下面方法行不通
//        JSONObject params = new JSONObject();
//        params.put("key","56b460780912b680effd921b027de661");
//        params.put("team","拜仁");
        //StringEntity entity = new StringEntity(params.toString(), ContentType.APPLICATION_FORM_URLENCODED);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        NameValuePair keyParam = new BasicNameValuePair("key","56b460780912b680effd921b027de661");
        NameValuePair teamParam = new BasicNameValuePair("team","拜仁");
        nameValuePairs.add(keyParam);
        nameValuePairs.add(teamParam);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, StandardCharsets.UTF_8);
        post.setEntity(urlEncodedFormEntity);
        try {
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity1 = response.getEntity();
            System.out.println(EntityUtils.toString(entity1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
