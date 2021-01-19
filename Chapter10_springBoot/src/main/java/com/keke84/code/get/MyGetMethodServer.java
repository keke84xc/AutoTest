package com.keke84.code.get;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @ApiOperation(value = "获取cookies的接口",httpMethod = "GET")
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
    @ApiOperation(value = "要携带cookie的get请求",httpMethod = "GET")
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

    /**
     * 第一种需要携带参数访问的get请求
     * 访问方式：host:port/get/with/param?start=x&end=x
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/get/with/param")
    @ApiOperation(value = "第一种需要携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getProductList(@RequestParam Integer start,@RequestParam Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList;

    }

    /**
     * 第二种需要携带参数访问的get请求
     * http://localhost:8098/get/with/param/1/19
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/get/with/param/{start}/{end}")
    @ApiOperation(value = "第二种需要携带参数访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getProductList2(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList;
    }
}
