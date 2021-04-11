package com.keke84.boot.controller;

import com.keke84.boot.mapper.UserMapper;
import com.keke84.boot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

//@Log4j
@RestController
@RequestMapping("/v1")
@Api(value = "v1",description = "用户管理系统")
public class UserManager {

    @Autowired(required = false)
    private UserMapper userMapper;

    @PostMapping("/getUserById")
    @ApiOperation(value = "测试springBoot+mybatis",httpMethod = "POST")
    public User getUserById(Integer uid){
        User userById = userMapper.findUserById(uid);
        return userById;
    }

    @PostMapping("/login")
    @ApiOperation(value = "01登录接口",httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        //System.out.println(user);
        User user1 = userMapper.findUserByUserNameAndPwd(user.getUserName(), user.getPassword());

        //log.info("查到的用户是:"+user1);
        if (null != user1){
            //登录成功，设置cookies
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "02添加用户接口",httpMethod = "POST")
    public Boolean addUser(HttpServletRequest request, @RequestBody User user, @CookieValue("login") String cookieValue){
        System.out.println(user);
        Boolean verifyCookies = verifyCookies(request.getCookies());
        if (!verifyCookies){
            return false;
        }
        //传递null或者"null"对象属性都是null
        if (null == user.getSex() || null == user.getPermission() || null == user.getIsDelete()){
            System.out.println("sex/permission/isDelete数据非法，为null");
            return false;
        }

        if (user.getSex().getClass().isInstance(Boolean.class) && user.getPermission().getClass().isInstance(Boolean.class) && user.getIsDelete().getClass().isInstance(Boolean.class)){
            System.out.println("sex/permission/isDelete数据非法，不是Boolean类型");
            return false;
        }
        int i = userMapper.saveUser(user);
        return i>0?true:false;
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "03返回用户列表接口",httpMethod = "POST")
    public List<User> getUserList(HttpServletRequest request){
        Boolean verifyCookies = this.verifyCookies(request.getCookies());
        if (!verifyCookies){
            return null;
        }
        List<User> users = userMapper.getUsers();
        return users;
    }

    @PostMapping("/getUserListByUser")
    @ApiOperation(value = "05返回用户列表接口，条件sql",httpMethod = "POST")
    public List<User> getUserListByUser(HttpServletRequest request,@RequestBody() User user){
        System.out.println(user);
        Boolean verifyCookies = this.verifyCookies(request.getCookies());
        if (!verifyCookies){
            return null;
        }
        List<User> users = userMapper.getUsersByUser(user);
        return users;
    }


    @PostMapping("/updateUser")
    @ApiOperation(value = "04更新用户接口",httpMethod = "POST")
    public boolean updateUser(HttpServletRequest request,@RequestBody User user){
        System.out.println(user);
        Boolean verifyCookies = this.verifyCookies(request.getCookies());
        if (!verifyCookies){
            System.out.println("cookie验证失败");
            return false;
        }
        int i = userMapper.updateUser(user);
        return i>0?true:false;
    }


    /**
     * 校验cookie的方法
     * @param cookies
     * @return
     */
    private Boolean verifyCookies(Cookie[] cookies){
        if (Objects.isNull(cookies)){
            System.out.println("cookies为空");
            return false;
        }else {
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                    System.out.println("cookie验证成功");
                    return true;
                }
            }
            System.out.println("cookie验证失败");
            return false;
        }
    }
}
