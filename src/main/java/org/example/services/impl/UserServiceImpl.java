package org.example.services.impl;

import org.example.data.request.ChangePasswordRequest;
import org.example.data.request.ListUserRequest;
import org.example.data.request.RegisterRequest;
import org.example.data.response.ListUserResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ProfileResponse;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IUserService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public int register(RegisterRequest request) {
        User userExist = userRepository.findByEmail(request.getEmail());

        if(userExist != null){
            return -1;
        }

        User newUser = new User();
        newUser.setUsername(request.getEmail());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFullName(request.getName());
        newUser.setImage(request.getImage());
        newUser.setPhone(request.getPhone());
        newUser.setAddress(request.getAddress());
        newUser.setStatus("Active");
        Role userRole = roleRepository.findById("USER").get();
        newUser.setRole(userRole);

        userRepository.save(newUser);
        return 1;
    }

    @Override
    public ProfileResponse profile() {
        String currentUsername = CommonUtils.getCurrentUsername();
        User user = userRepository.findByUsername(currentUsername).get();

        ProfileResponse profile = new ProfileResponse();
        profile.setUsername(currentUsername);
        profile.setFullName(user.getFullName());
        profile.setAddress(user.getAddress());
        profile.setPhone(user.getPhone());
        profile.setEmail(user.getEmail());
        profile.setImage(user.getImage());

        return profile;
    }

    @Override
    public int changePassword(ChangePasswordRequest request) {
        String currentUsername = CommonUtils.getCurrentUsername();
        User user = userRepository.findByUsername(currentUsername).get();

        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            return -1;
        }

        if(!request.getNewPassword().equals(request.getOldPassword())){
            return 0;
        }

        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());

        user.setPassword(encodedNewPassword);
        userRepository.save(user);

        return 1;
    }

    @Override
    public PaginationVO<ListUserResponse> list(ListUserRequest request) {
        List<User> userList = userRepository.filter(
                request.getName(),
                request.getRole(),
                request.getStatus(),
                request.getPageSize(),
                request.getPageNumber());

        int count = userRepository.count(
                request.getName(),
                request.getRole(),
                request.getStatus()
        );

        List<ListUserResponse> petResponses = userList.stream()
                .map(ListUserResponse::fromUser)
                .collect(Collectors.toList());

        PaginationVO<ListUserResponse> responsePaginationVO = new PaginationVO<>(
                count, petResponses
        );

        return responsePaginationVO;
    }


}
