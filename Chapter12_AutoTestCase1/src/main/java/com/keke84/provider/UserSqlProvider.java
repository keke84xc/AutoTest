package com.keke84.provider;

import com.keke84.model.GetUserInfoCase;
import com.keke84.model.GetUserListCase;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String selectByGetUserListCase(GetUserListCase getUserListCase){

        return new SQL(){
            {
                SELECT("*");
                FROM("tb_user");
                if (getUserListCase != null) {
                    if (null != getUserListCase.getUserName() && getUserListCase.getUserName() != "") {
                        WHERE("username like '%' #{userName} '%'");
                    }

                    if (getUserListCase.getAge() != 0) {
                        WHERE("age = #{age}");
                    }

                    if (null != getUserListCase.getSex() && !getUserListCase.getSex().equals("") && !getUserListCase.getSex().equals("null") && !getUserListCase.getSex().equals("NULL")){

                        Integer sex = 0;

                        if (getUserListCase.getSex().equals("true") || getUserListCase.getSex().equals("false")){

                            boolean b = Boolean.parseBoolean(getUserListCase.getSex());

                            sex = b?1:0;

                        }else if (getUserListCase.getSex().equals("1") || getUserListCase.getSex().equals("0")){


                            sex = Integer.parseInt(getUserListCase.getSex());

                        }else {
                            //当tb_getuserlistcase表的sex字段不是1、0、true、false、‘’、null时，也尝试将其转为int

                            try {
                                sex = Integer.parseInt(getUserListCase.getSex());
                            }catch (Exception e){

                                System.out.println("getUserListCase.getSex()数据不合法，无法转为Integer");
                            }
                        }
                        WHERE("sex = "+sex);
                    }

                }
            }
        }.toString();
    }



    public String selectByGetUserInfoCase(GetUserInfoCase getUserInfoCase){

        return new SQL(){

            {
                SELECT("*");
                FROM("tb_user");
                if (getUserInfoCase != null) {

                    if (getUserInfoCase.getUserId() != 0){
                        WHERE("id = #{userId}");
                    }

                }
            }
        }.toString();

    }


}
