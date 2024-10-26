package org.example.controller;

import org.example.data.request.*;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ResponseData;
import org.example.data.response.ViewAdoptResponse;
import org.example.services.interfaces.IAdoptService;
import org.example.services.interfaces.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdoptController {

    @Autowired
    private IPetService petService;
    @Autowired
    private IAdoptService adoptService;

    //cho pet
    @PostMapping("/pet/create")
    public ResponseEntity<ResponseData<String>> create(@RequestBody CreatePetRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("ADD_PET_SUCCESS",
                "Add pet successful", null);
        return ResponseEntity.ok(responseData);
    }

    // nhận nuôi
    @PostMapping("/adopt/create")
    public ResponseEntity<ResponseData<String>> createAdopt(@RequestBody CreateAdoptionRequest request) {
        adoptService.create(request);

        ResponseData<String> responseData = new ResponseData<>("REQUEST_ADOPT_PET_SUCCESS",
                "Request adopt pet successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/changeStatus")
    public ResponseEntity<ResponseData<String>> confirmAdopt(@RequestBody ChangeStatusAdoptRequest request) {
        int result = adoptService.changeStatus(request);

        ResponseData<String> responseData = null;
        if(result == -1){
            responseData = new ResponseData<>("DON'T HAVE PERMISSIONS",
                    "You don't have permission. Please contact to admin", null);
        } else{
            responseData = new ResponseData<>("CHANGE_STATUS_ADOPT_SUCCESS",
                    "Change status of adopt successful", null);
        }

        return ResponseEntity.ok(responseData);
    }


    @PostMapping("/adopt/history")
    public ResponseEntity<ResponseData<PaginationVO<AdoptHistoryResponse>>> history(@RequestBody AdoptHistoryRequest request) {
        PaginationVO<AdoptHistoryResponse> responsePaginationVO =  adoptService.history(request);

        ResponseData<PaginationVO<AdoptHistoryResponse>> responseData = new ResponseData<>("GET_ADOPT_HISTORY_SUCCESS",
                "Get pet adoption history successful", responsePaginationVO);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/update")
    public ResponseEntity<ResponseData<String>> update(@RequestBody UpdateAdoptionRequest request) {
        petService.update(request);

        ResponseData<String> responseData = new ResponseData<>("UPDATE_ADOPT_SUCCESS",
                "Update pet adoption successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/delete")
    public ResponseEntity<ResponseData<String>> delete(@RequestParam int adoptId) {
        petService.delete(adoptId);

        ResponseData<String> responseData = new ResponseData<>("DELETE_ADOPT_SUCCESS",
                "Delete pet adoption history successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/public/adopt/view")
    public ResponseEntity<ResponseData<ViewAdoptResponse>> view(@RequestParam int adoptId) {
        ViewAdoptResponse response = adoptService.view(adoptId);

        ResponseData<ViewAdoptResponse> responseData = new ResponseData<>("VIEW_ADOPT_SUCCESS",
                "View pet adoption detail successful", response);
        return ResponseEntity.ok(responseData);
    }
}
