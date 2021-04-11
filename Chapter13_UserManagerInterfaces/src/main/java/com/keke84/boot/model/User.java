package com.keke84.boot.model;
import lombok.Data;
@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int age;
    //Boolean类型对象可以接收null值，当该属性为null，则动态sql不包含该查询项
    private Boolean sex;
    private Boolean permission;
    private Boolean isDelete;
}
