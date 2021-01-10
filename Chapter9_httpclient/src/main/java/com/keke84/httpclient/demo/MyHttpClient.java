package com.keke84.httpclient.demo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyHttpClient {

    @Test
    public void test1(){

        String res = "";
        String url = "https://www.baidu.com";
        HttpGet get = new HttpGet(url);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse execute = httpClient.execute(get);
            //Http返回结果实体，不含Header内容
            HttpEntity entity = execute.getEntity();
            //1.获取body
            res = EntityUtils.toString(entity,"utf-8");

            //2.获取header
            //获取所有header
            /*Header[] allHeaders = execute.getAllHeaders();
            for (Header header:allHeaders) {
                String header_str = header.toString();
                System.out.println(header_str);
            }*/
            //getFirstHeaderhe和getLastHeader区别，当有多个header满足给的key时，1个返回第一个，1个返回最后1个
            Header firstHeader = execute.getFirstHeader("Content-Type");
            Header lastHeader = execute.getLastHeader("Content-Type");
            //Content-Type: text/html
            System.out.println(firstHeader.toString());
            System.out.println(lastHeader.toString());
            System.out.println(res);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
