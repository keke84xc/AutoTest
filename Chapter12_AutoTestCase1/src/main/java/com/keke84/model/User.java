package com.keke84.model;
import lombok.Data;
@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int age;
    private boolean sex;
    private boolean permission;
    private boolean isDelete;


    @Override
    public String toString() {
        return ("{"+
                "id:"+id+","+
                "userName:"+userName+","+
                "password:"+password+","+
                "age:"+age+","+
                "sex:"+sex+","+
                "permission:"+permission+","+
                "isDelete:"+isDelete
                +"}"
                );
    }
}
