package org.example.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteerDetailResponse {
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String status;
    private String dob;
    private String cccd;
    private String experience;
    private String currentJob;
    private String reason;
    private String gender;
}
