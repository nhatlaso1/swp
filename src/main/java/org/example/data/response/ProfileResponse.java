package org.example.data.response;

import lombok.Data;

@Data
public class ProfileResponse {
    private String username;
    private String fullName;
    private String email;
    private String image;
    private String phone;
    private String address;
}
