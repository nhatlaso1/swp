package org.example.data.request;

import lombok.Data;

@Data
public class ConfirmVolunteerRequest {
    private int volunteerId;
    private String status;

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
