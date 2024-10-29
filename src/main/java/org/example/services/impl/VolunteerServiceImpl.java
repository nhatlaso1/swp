package org.example.services.impl;

import org.example.data.request.ConfirmApplicationRequest;
import org.example.data.request.RegisterVolunteerRequest;
import org.example.data.response.VolunteerDetailResponse;
import org.example.entities.Adoption;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.entities.Volunteer;
import org.example.model.VolunteerDetailVO;
import org.example.repositories.*;
import org.example.services.interfaces.IVolunteerService;
import org.example.utils.CommonUtils;
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
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;

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
        newUser.setImage(request.getImage());
        newUser.setAddress(request.getAddress());
        newUser.setStatus("Active");

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

    @Override
    public VolunteerDetailResponse detail(int userId) {
        VolunteerDetailVO detailVO = volunteerRepository.findByUserId(userId);

        VolunteerDetailResponse response = VolunteerDetailResponse.builder()
                .gender(detailVO.getGender())
                .reason(detailVO.getReason())
                .currentJob(detailVO.getCurrentJob())
                .dob(detailVO.getDob())
                .phone(detailVO.getPhone())
                .cccd(detailVO.getCccd())
                .email(detailVO.getEmail())
                .address(detailVO.getAddress())
                .status(detailVO.getStatus())
                .fullName(detailVO.getFullName())
                .experience(detailVO.getExperience())
                .build();

        return response;
    }

    @Override
    public int changeStatus(ConfirmApplicationRequest request) {
        Adoption adoption = adoptionRepository.findById(request.getApplicationId()).orElse(null);

        if(adoption == null){
            return -2;
        }

        String username = CommonUtils.getCurrentUsername();
        User currentUser = userRepository.findByUsername(username).get();

        if(currentUser.getRole().getId().equals("USER")){
            return -1;
        }

        applicationRepository.changeStatus(request.getStatus(), currentUser.getId(), request.getApplicationId());

        return 0;
    }

}
