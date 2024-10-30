package org.example.services.interfaces;

import org.example.data.request.ChangePasswordRequest;
import org.example.data.request.ListUserRequest;
import org.example.data.request.RegisterRequest;
import org.example.data.request.UpdateProfileRequest;
import org.example.data.response.ListUserResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ProfileResponse;
import org.example.entities.User;

public interface IUserService {
    User findByUsername(String username);
    int register(RegisterRequest request);
    ProfileResponse profile();
    ProfileResponse getProfile(int id);
    int updateProfile(UpdateProfileRequest request);
    int changePassword(ChangePasswordRequest request);
    PaginationVO<ListUserResponse> list(ListUserRequest request);
}
