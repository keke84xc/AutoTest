package com.keke84.boot.mapper;

import com.keke84.boot.model.User;
import com.keke84.boot.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from tb_user where id=#{id}")
    public User findUserById(Integer id);


    /**
     * 1、登录接口查询
     * @param name
     * @param pwd
     * @return
     */
    @Select("select * from tb_user where userName=#{name} and password=#{pwd}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "userName",property = "userName"),
            @Result(column = "password",property = "password"),
    })
    public User findUserByUserNameAndPwd(@Param("name")String name,@Param("pwd")String pwd);

    /**
     * 2、添加用户
     * @param user
     * @return
     */
    @Insert("insert into tb_user values(0,#{userName},#{password},#{age},#{sex},#{permission},#{isDelete})")
    public int saveUser(User user);

    /**
     * 3、获取所有用户列表
     * @return
     */
    @Select("select * from tb_user")
    public List<User> getUsers();

    /**
     * 5、获取需要查询的用户列表，条件sql
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class,method = "select")
    public List<User> getUsersByUser(User user);

    //update tb_user set userName='fff',password='ccc',age=12,sex=1,permission=1,isDelete=0 where id=6;

    /**
     * 4、更新用户
     * @param user
     * @return
     */
    @UpdateProvider(type = UserSqlProvider.class,method = "update")
    public int updateUser(User user);
}
