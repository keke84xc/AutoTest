package com.keke84.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private boolean sex;
    private int age;
    private boolean permission;
    private boolean isDelete;
    private String expected;
}
