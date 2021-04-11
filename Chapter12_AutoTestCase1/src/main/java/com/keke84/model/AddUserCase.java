package com.keke84.model;

import lombok.Data;

@Data
public class AddUserCase {

    private String userName;
    private String password;
    private int age;
    private String sex;
    private String permission;
    private String isDelete;
    private String expected;

}
