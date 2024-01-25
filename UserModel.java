package com.example.helloworld.request;

import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String userName;
    private String password;
    private String email;

}