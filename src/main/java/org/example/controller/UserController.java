package org.example.controller;

import org.example.data.request.AdoptHistoryRequest;
import org.example.data.request.ChangePasswordRequest;
import org.example.data.request.ListUserRequest;
import org.example.data.response.*;
import org.example.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/profile")
    public ResponseEntity<ResponseData<ProfileResponse>> profile() {
        ResponseData<ProfileResponse> responseData = new ResponseData<>("GET_PROFILE_SUCCESS"
                , "Get profile successful", userService.profile());

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseData<String>> changePassword(@RequestBody ChangePasswordRequest request) {
        int result = userService.changePassword(request);
        ResponseData<String> responseData = null;
        if(result == -1){
            responseData = new ResponseData<>("OLD_PASSWORD_INCORRECT"
                    , "Old password incorrect. Please try again!", null);
        }
        else if(result == 0){
            responseData = new ResponseData<>("PASSWORD_NOT_MATCH"
                    , "Password not match. Please try again!", null);
        }

        responseData = new ResponseData<>("CHANGE_PASSWORD_SUCCESS"
                , "Change password successful.", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseData<PaginationVO<ListUserResponse>>> list(@RequestBody ListUserRequest request) {
        PaginationVO<ListUserResponse> responsePaginationVO =  userService.list(request);

        ResponseData<PaginationVO<ListUserResponse>> responseData = new ResponseData<>("LIST_USER_SUCCESS",
                "List user successful", responsePaginationVO);
        return ResponseEntity.ok(responseData);
    }

}
