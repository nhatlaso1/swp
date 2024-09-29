package org.example.services.interfaces;

import org.example.entities.User;

public interface IUserService {
    User findByUsername(String username);
}
