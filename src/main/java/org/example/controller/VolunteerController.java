package org.example.controller;

import org.example.data.request.ConfirmVolunteerRequest;
import org.example.data.request.RegisterVolunteerRequest;
import org.example.data.response.ResponseData;
import org.example.data.response.VolunteerDetailResponse;
import org.example.entities.EmailDetails;
import org.example.entities.Volunteer;
import org.example.model.VolunteerDetailVO;
import org.example.repositories.VolunteerRepository;
import org.example.services.interfaces.IEmailService;
import org.example.services.interfaces.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VolunteerController {

    @Autowired
    private IVolunteerService volunteerService;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private IEmailService emailService;
    @PostMapping("/public/volunteers/register")
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

    @PostMapping("/volunteers/detail")
    public ResponseEntity<ResponseData<VolunteerDetailResponse>> detail(@RequestParam int userId) {
        ResponseData<VolunteerDetailResponse> responseData = new ResponseData<>("DETAIL_VOLUNTEER_SUCCESS"
                , "Get volunteer detail information successful", volunteerService.detail(userId));

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/volunteers/confirm")
    public ResponseEntity<ResponseData<Integer>> confirm(@RequestBody ConfirmVolunteerRequest request) {

        volunteerRepository.confirmStatus(request.getStatus(), request.getVolunteerId());
        VolunteerDetailVO volunteer = volunteerRepository.findByUserId(request.getVolunteerId());
        String mess = null;
        if(request.getStatus().equals("Accept")){
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setMsgBody("Đơn của bạn đã được chấp nhận");
            emailDetails.setRecipient(volunteer.getEmail());
            emailDetails.setSubject("THÔNG BÁO TRẠNG THÁI ĐƠN CỦA BẠN");
            emailService.sendSimpleMail(emailDetails);
            mess = "ACCEPT_VOLUNTEER_SUCCESS";
        } else if(request.getStatus().equals("Reject")){
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setMsgBody("Đơn của bạn đã bị từ chối");
            emailDetails.setRecipient(volunteer.getEmail());
            emailDetails.setSubject("THÔNG BÁO TRẠNG THÁI ĐƠN CỦA BẠN");
            emailService.sendSimpleMail(emailDetails);
            mess = "REJECT_VOLUNTEER_SUCCESS";
        }

        ResponseData<Integer> responseData = new ResponseData<>(mess
                , "Confirm volunteer successful", null);

        return ResponseEntity.ok(responseData);
    }

}
