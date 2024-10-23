package org.example.data.response;

import lombok.Data;
import org.example.entities.User;

@Data
public class ListUserResponse {
    private int id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String status;
    private String statusApplication;
    private String role;
    private String image;

    public static ListUserResponse fromUser(User user) {
        ListUserResponse response = new ListUserResponse();
        response.setId(user.getId());
        response.setAddress(user.getAddress());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setPhone(user.getPhone());
        response.setStatus(user.getStatus());
        response.setRole(user.getRole().getName());
        response.setStatusApplication(user.getVolunteer() == null ? null : user.getVolunteer().getStatus());
        response.setImage(user.getImage());

        return response;
    }
}
