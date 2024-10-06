package org.example.controller;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.response.ResponseData;
import org.example.services.interfaces.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdoptController {

    @Autowired
    private IPetService petService;

    @PostMapping("/pet/create")
    public ResponseEntity<ResponseData<String>> create(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("FILTER_PET_SUCCESS", "Filter pet successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/pet/history")
    public ResponseEntity<ResponseData<String>> history(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("GET_ADOPT_HISTORY_SUCCESS", "Get pet adoption history successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/pet/update")
    public ResponseEntity<ResponseData<String>> update(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("UPDATE_ADOPT_SUCCESS", "Update pet adoption successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/pet/delete")
    public ResponseEntity<ResponseData<String>> delete(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("DELETE_ADOPT_SUCCESS", "Delete pet adoption history successful", null);
        return ResponseEntity.ok(responseData);
    }
}
