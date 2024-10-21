package org.example.controller;

import org.example.data.request.RegisterVolunteerRequest;
import org.example.data.response.ResponseData;
import org.example.services.interfaces.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/volunteers")
public class VolunteerController {

    @Autowired
    private IVolunteerService volunteerService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Integer>> register(@RequestBody RegisterVolunteerRequest request) {

        int x = volunteerService.register(request);
        ResponseData<Integer> responseData = null;
        if(x == -1){
            responseData = new ResponseData<>("EMAIL_ALREADY_EXIST"
                    , "Email already exist. Please try again.",null);
        } else if (x == 1){
            responseData = new ResponseData<>("REGISTER_SUCCESS"
                    , "Register successful", null);
        }

        return ResponseEntity.ok(responseData);
    }
}
