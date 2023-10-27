package com.example.helloworld.request;

import lombok.Data;

@Data
public class UserQueryModel {

    private Long id;
    private String userName;
    private String password;
    private String email;

}
