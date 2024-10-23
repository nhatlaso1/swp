package org.example.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVolunteerRequest {
    private String username;
    private String password;
    private String fullName;
    private String image;
    private String email;
    private String phone;
    private String address;
    private String dob;
    private String gender;
    private String cccd;
    private String experience;
    private String currentJob;
    private String reason;
}
