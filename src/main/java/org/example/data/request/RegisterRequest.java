package org.example.data.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String rePassword;
    private String image;
    private String phone;
    private String address;
}
