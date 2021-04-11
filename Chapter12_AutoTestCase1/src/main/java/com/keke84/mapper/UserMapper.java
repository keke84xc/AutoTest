package com.keke84.mapper;

import com.keke84.model.*;
import com.keke84.provider.UserSqlProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.testng.annotations.Test;

import java.util.List;

public interface UserMapper {

    @Select("select * from tb_user where id=#{id}")
    public User findUserById(Integer id);

    //获取登录接口case数据
    @Select("select * from tb_loginCase where id=#{id}")
    public LoginCase findLoginCaseById(Integer id);

    //获取添加用户接口case数据
    @Select("select * from tb_addUserCase where id=#{id}")
    public AddUserCase findAddUserCaseById(Integer id);

    //获取查询用户列表接口case数据
    @Select("select * from tb_getUserListCase where id=#{id}")
    public GetUserListCase findGetUserListCaseById(Integer id);

    //获取查询用户信息接口case数据
    @Select("select * from tb_getUserInfoCase where id=#{id}")
    public GetUserInfoCase findGetUserInfoCaseById(Integer id);

    //获取更新/删除用户接口case数据
    @Select("select * from tb_updateUserInfoCase where id=#{id}")
    public UpdateUserInfoCase findUpdateUserInfoCaseById(Integer id);

    @SelectProvider(type = UserSqlProvider.class,method = "selectByGetUserListCase")
    public List<User> getUserList(GetUserListCase getUserListCase);


    @SelectProvider(type = UserSqlProvider.class,method = "selectByGetUserInfoCase")
    public List<User> getUserByGetUserInfoCase(GetUserInfoCase getUserInfoCase);

}
