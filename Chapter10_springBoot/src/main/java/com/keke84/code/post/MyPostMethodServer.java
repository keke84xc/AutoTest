package com.keke84.code.post;

import com.keke84.code.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
//类中添加RequestMapping注解，请求url中要加上v1，例如：
@RequestMapping("/v1")
public class MyPostMethodServer {
    private static Cookie cookie;
    //用户登录成功获取到cookies，然后再携带cookie访问其他接口
    @PostMapping("/login")
    @ApiOperation(value = "登录接口，成功后获取cookies",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        //value的值是客户端传过来的参数名，required表示必填
                        //form表单的post请求
                        @RequestParam(value = "userName",required = true)String username,
                        @RequestParam(value = "passWord",required = true)String password){

        if (username.equals("zhangsan") && password.equals("123456")){
            cookie = new Cookie("login","true");

            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或密码错误";
    }


    @PostMapping("/login2")
    public String moreCookies(HttpServletResponse response,
                              @RequestParam(value = "userName")String username,
                              @RequestParam(value = "passWord")String password){

        if (username.equals("zhangsan") && password.equals("123456")){
            Cookie cookie1 = new Cookie("sessionId","akdhaajkdgjadadakdlalda==");
            Cookie cookie2 = new Cookie("login","true");

            response.addCookie(cookie1);
            response.addCookie(cookie2);

            return "恭喜你登录成功了";
        }
        return "用户名或密码错误";
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
        User user1;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "参数不合法";
        }
        for (Cookie currentCookie:cookies){
            if (currentCookie.getName().equals("login") &&
                    currentCookie.getValue().equals("true") &&
                    user.getUsername().equals("zhangsan") &&
                    user.getPassword().equals("123456"))
            {
                user1 = new User();
                user1.setName("lisi");
                user1.setAge(18);
                user1.setGender("male");
                return user1.toString();
            }
        }
        return "参数不合法";
    }
}
