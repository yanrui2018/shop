package cn.yanrui.shop.entity;


import lombok.Data;


@Data
//@Entity
//@Table(name = "user")
public class User {
    private String id;

    private String username;

    private String password;

    private String phone;

    private String role;



}
