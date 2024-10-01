package org.example.services.interfaces;

import org.example.data.request.RegisterRequest;
import org.example.entities.User;

public interface IUserService {
    User findByUsername(String username);
    int register(RegisterRequest request);
}
