package org.example.services.impl;

import org.example.data.request.RegisterRequest;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        newUser.setPhone(request.getPhone());
        newUser.setAddress(request.getAddress());

        userRepository.save(newUser);
        return 1;
    }
}
