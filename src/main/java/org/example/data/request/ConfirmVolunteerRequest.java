package org.example.data.request;

import lombok.Data;

@Data
public class ConfirmVolunteerRequest {
    private int volunteerId;
    private String status;
}
