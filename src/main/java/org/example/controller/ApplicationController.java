package org.example.controller;


import org.example.data.request.ConfirmApplicationRequest;
import org.example.data.response.ResponseData;
import org.example.repositories.ApplicationRepository;
import org.example.services.interfaces.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private IVolunteerService volunteerService;

    @PostMapping("/adopt/application/changeStatus")
    public ResponseEntity<ResponseData<String>> confirmApplication(@RequestBody ConfirmApplicationRequest request) {

        int result = volunteerService.changeStatus(request);

        ResponseData<String> responseData = null;
        if(result == -1){
            responseData = new ResponseData<>("DON'T HAVE PERMISSIONS",
                    "You don't have permission. Please contact to admin", null);
        } else if(result == -2){
            responseData = new ResponseData<>("ADOPTION_DOESN'T_EXIST",
                    "Adoption doesn't exist", null);
            return ResponseEntity.ok(responseData);
        }else{
            responseData = new ResponseData<>("CHANGE_STATUS_APPLICATION_SUCCESS",
                    "Change status of application successful", null);
        }

        return ResponseEntity.ok(responseData);
    }
}
