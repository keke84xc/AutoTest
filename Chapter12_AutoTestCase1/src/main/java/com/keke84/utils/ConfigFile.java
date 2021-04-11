package com.keke84.utils;

import com.keke84.model.InterfaceName;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.ResourceBundle;


public class ConfigFile {

    //注意不要写成：application.properties
    private static ResourceBundle bundle = ResourceBundle.getBundle("application");
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(name == InterfaceName.ADDUSERINFO){
            uri = bundle.getString("addUser.uri");
        }
        testUrl = address + uri;
        return testUrl;

    }


    public static void main(String[] args) throws IOException {

        String url = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        //http://localhost:8098/v1/updateUserInfo
        System.out.println(url);

    }

}
