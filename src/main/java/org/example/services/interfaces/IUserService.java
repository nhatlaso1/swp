package org.example.services.interfaces;

import org.example.data.request.ChangePasswordRequest;
import org.example.data.request.RegisterRequest;
import org.example.data.response.ProfileResponse;
import org.example.entities.User;

public interface IUserService {
    User findByUsername(String username);
    int register(RegisterRequest request);
    ProfileResponse profile();
    int changePassword(ChangePasswordRequest request);
}
