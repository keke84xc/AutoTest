package com.keke84.boot.provider;
import com.keke84.boot.model.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {
    //update tb_user set userName='fff',password='ccc',age=12,sex=1,permission=1,isDelete=0 where id=6;
    public String update(User user){
        return new SQL(){
            {
                UPDATE("tb_user");
                if (user.getUserName() != null && user.getUserName() != "") {
                    SET("userName = #{userName}");
                }
                if (user.getPassword() != null && user.getPassword() != "") {
                    SET("password = #{password}");
                }
                if (user.getAge() != 0) {
                    SET("age = #{age}");
                }

                if (null != user.getSex() && (user.getSex() == true || user.getSex() == false)){
                    SET("sex = #{sex}");
                }

                if (null != user.getPermission() && (user.getPermission() == true || user.getPermission() == false)){
                    SET("permission = #{permission}");
                }

                if (null != user.getIsDelete() && (user.getIsDelete() == true || user.getIsDelete() == false)){
                    SET("isDelete = #{isDelete}");
                }
                WHERE("id = #{id}");
            }

        }.toString();

    }

    public String select(User user){

        return new SQL(){
            {
                SELECT("*");
                FROM("tb_user");
                if (user != null) {
                    if (user.getId() != 0) {
                        WHERE("id = #{id}");
                    }
                    if (null != user.getUserName() && user.getUserName() != "") {
                        WHERE("username like '%' #{userName} '%'");
                    }
                    if (user.getPassword() != null && user.getPassword() != "") {
                        WHERE("password = #{password}");
                    }
                    if (user.getAge() != 0) {
                        WHERE("age = #{age}");
                    }
//                    if (user.isSex() == true || user.isSex() == false){
//                        WHERE("sex = #{sex}");
//                    }

                    if (null != user.getSex() && (user.getSex() == true || user.getSex() == false)){
                        WHERE("sex = #{sex}");
                    }

                    if (null != user.getPermission() && (user.getPermission() == true || user.getPermission() == false)){
                        WHERE("permission = #{permission}");
                    }
                    if (null != user.getIsDelete() && (user.getIsDelete() == true || user.getIsDelete() == false)){
                        WHERE("isDelete = #{isDelete}");
                    }
                }
            }
        }.toString();
    }
}
