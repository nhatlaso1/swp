package org.example.services.impl;

import org.example.data.request.RegisterVolunteerRequest;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.entities.Volunteer;
import org.example.repositories.RoleRepository;
import org.example.repositories.UserRepository;
import org.example.repositories.VolunteerRepository;
import org.example.services.interfaces.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements IVolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int register(RegisterVolunteerRequest request) {
        User userExist = userRepository.findByEmail(request.getEmail());

        if (userExist != null) {
            return -1;
        }

        User newUser = new User();
        newUser.setUsername(request.getEmail());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFullName(request.getFullName());
        newUser.setPhone(request.getPhone());
        newUser.setAddress(request.getAddress());

        Role userRole = roleRepository.findById("VOLUNTEER").orElseThrow(() -> new RuntimeException("Role không tồn tại"));
        newUser.setRole(userRole);

        userRepository.save(newUser);

        Volunteer volunteer = new Volunteer();
        volunteer.setDob(request.getDob());
        volunteer.setCccd(request.getCccd());
        volunteer.setExperience(request.getExperience());
        volunteer.setCurrentJob(request.getCurrentJob());
        volunteer.setGender(request.getGender());
        volunteer.setStatus("Waiting");
        volunteer.setReason(request.getReason());
        volunteer.setUser(newUser);

        volunteerRepository.save(volunteer);

        return 1;
    }

}
