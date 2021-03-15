package com.keke84.code.get;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethodServer {

    /**
     * 返回cookies的get请求
     * @param response
     * @return
     */
    @GetMapping("/getCookies")
    @ApiOperation(value = "获取cookies的get接口",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获取cookies信息成功！！";
    }

    /**
     * 需要携带cookie的get请求
     * @param request
     * @return
     */
    @GetMapping("/get/with/cookies")
    @ApiOperation(value = "要携带cookie才能访问的get请求",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "访问失败，cookies校验失败";
        }
        for (Cookie cookie:cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (name.equals("login") && value.equals("true")){
                return "访问成功，这是1个需要cookies的get请求";
            }
        }
        return "访问失败，cookies校验失败";
    }

    //该方法不好取每个cookie
//    @GetMapping("/get/with/cookies2")
//    @ApiOperation(value = "要携带cookie的get请求2",httpMethod = "GET")
//    public String getWithCookies2(@RequestHeader(value = "Cookie") String cookies){
//        if (Objects.isNull(cookies)){
//            return "访问失败，cookies校验失败";
//        }
//        //login=true; login2=false
//        System.out.println(cookies);
//        return cookies;
//    }

    @GetMapping("/get/with/cookies3")
    @ApiOperation(value = "要携带cookie才能访问的get请求3",httpMethod = "GET")
    public String getWithCookies3(@CookieValue(value = "login") String cookie){
        if (Objects.isNull(cookie)){
            return "访问失败，cookies校验失败";
        }
        //true
        System.out.println(cookie);
        if(cookie.equals("true")){
            return "访问成功，这是1个需要cookies的get请求";
        }else {
            return "访问失败，cookies校验失败";
        }
    }


    /**
     * 第一种需要携带参数访问的get请求
     * 访问方式：host:port/get/with/param?start=x&end=x
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/get/with/param")
    @ApiOperation(value = "第一种需要携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Object> getProductList(@RequestParam Integer start,@RequestParam Integer end){

        Map<String,Object> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList;

    }

    /**
     * 第二种Rest风格的需要携带参数访问的get请求
     * http://localhost:8098/get/with/param/1/19
     * @param startNum
     * @param endNum
     * @return
     */
    @GetMapping("/get/with/param/{start}/{end}")
    @ApiOperation(value = "第二种需要携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getProductList2(@PathVariable(value = "start") Integer startNum,
                                               @PathVariable(value = "end") Integer endNum){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        myList.put("startNum",startNum);
        myList.put("endNum",endNum);
        return myList;
    }
}
