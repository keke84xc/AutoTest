package com.course.mapper;

import com.course.model.User;
import com.course.model.User2;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    //@Select("select count(*) from user")
    public int getUserCount();

    @Select("select count(*) from user")
    public int getUserCountByAnno();


    public int saveUserByXml(User user);

    @Insert("insert into user values(#{id},#{username},#{age},#{sex})")
    public int saveUserByAnno(@Param("id")Integer id,
                        @Param("username")String name,
                        @Param("age")Integer age,
                        @Param("sex")String sex);


    public int updateUserByXml(User user);


    @Update("update user set name=#{name},age=#{age},sex=#{sex} where id=#{id}")
    public int updateUserByAnno(User user);

    @Select("select * from user where id=#{id} and name=#{name}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "username"),
            @Result(column = "age",property = "age"),
            @Result(column = "sex",property = "sex")
    })
    public User2 findUserByIdAndUsername(@Param("id")Integer id,@Param("name")String username);


    @Delete("delete from user where id=#{id}")
    public int delUserById(@Param("id")Integer id);

    @Delete("delete from user where id=#{id}")
    public int delUserByUser(User user);




}
