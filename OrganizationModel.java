package com.example.helloworld.request;

import lombok.Data;

@Data
public class OrganizationModel {
    private Long id;
    private String name;
    private String address;
    private String state;
    private String city;
    private String manager;
    private String phoneNumber;
    private String email;
}