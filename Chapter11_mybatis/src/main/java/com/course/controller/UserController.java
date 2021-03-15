package com.course.controller;


import com.course.mapper.UserMapper;
import com.course.model.User;
import com.course.model.User2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Log4j
@RestController
@Api(value = "v1",description = "第一个mybatis接口")
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private SqlSessionTemplate template;

    @Autowired(required = false)
    private UserMapper userMapper;

//    @GetMapping("/userCount")
//    @ApiOperation(value = "获取到用户数xml方式",httpMethod = "GET")
//    public int userCount(){
//        System.out.println("cscscs-----");
//
//        UserMapper mapper = template.getMapper(UserMapper.class);
//        int userCount = mapper.getUserCount();
//        return userCount;
//
//    }


    @GetMapping("/userCount2")
    @ApiOperation(value = "可以获取到用户数注解方式",httpMethod = "GET")
    public int userCount2(){
        int userCount = userMapper.getUserCountByAnno();
        return userCount;
    }

    @PostMapping("/saveUser")
    @ApiOperation(value = "添加用户：注解方式",httpMethod = "POST")
    public String saveUser(@RequestBody User user){

        System.out.println(user);
        int i = userMapper.saveUserByAnno(user.getId(), user.getName(), user.getAge(), user.getSex());
        return i>0?"success":"fail";

    }


    @PostMapping("/saveUser2")
    @ApiOperation(value = "添加用户：xml方式",httpMethod = "POST")
    public String saveUser2(@RequestBody User user){
        UserMapper mapper = template.getMapper(UserMapper.class);
        System.out.println(user);
        int i = userMapper.saveUserByXml(user);
        return i>0?"success":"fail";
    }


    @PostMapping("/updateUser1")
    @ApiOperation(value = "修改用户：xml方式",httpMethod = "POST")
    public String updateUser1(@RequestBody User user){
        UserMapper mapper = template.getMapper(UserMapper.class);
        System.out.println(user);
        int i = userMapper.updateUserByXml(user);
        return i>0?"success":"fail";
    }

    @PostMapping("/updateUser2")
    @ApiOperation(value = "修改用户：注解方式",httpMethod = "POST")
    public String updateUser2(@RequestBody User user){
        int i = userMapper.updateUserByAnno(user);
        return i>0?"success":"fail";
    }


    @PostMapping("/findUserByIdAndName")
    public User2 findUserByIdAndName(Integer id,String username){

        User2 userByIdAndUsername = userMapper.findUserByIdAndUsername(id, username);
        return userByIdAndUsername;

    }

    @PostMapping("/delUserByUserId")
    public String delUserByUserId(Integer id){

        int i = userMapper.delUserById(id);
        return i>0?"success":"fail";

    }

    @PostMapping("/delUserByUser")
    public String delUserByUser(@RequestBody User user){
        int i = userMapper.delUserByUser(user);
        return i>0?"success":"fail";
    }

}
