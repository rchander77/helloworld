package com.example.helloworld.request;

import lombok.Data;

@Data
public class RestaurantModel {
    private Long id;
    private String name;
    private String address;
    private String state;
    private String city;
    private String manager;
    private String phoneNumber;
    private String email;
    private String food;
    private String status;
}