package org.example.controller;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.request.UpdateAdoptionRequest;
import org.example.data.response.ResponseData;
import org.example.services.interfaces.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdoptController {

    @Autowired
    private IPetService petService;

    @PostMapping("/adopt/create")
    public ResponseEntity<ResponseData<String>> create(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("FILTER_PET_SUCCESS", "Filter pet successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/history")
    public ResponseEntity<ResponseData<String>> history(@RequestBody CreateAdoptionRequest request) {
        petService.create(request);

        ResponseData<String> responseData = new ResponseData<>("GET_ADOPT_HISTORY_SUCCESS", "Get pet adoption history successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/update")
    public ResponseEntity<ResponseData<String>> update(@RequestBody UpdateAdoptionRequest request) {
        petService.update(request);

        ResponseData<String> responseData = new ResponseData<>("UPDATE_ADOPT_SUCCESS", "Update pet adoption successful", null);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/adopt/delete")
    public ResponseEntity<ResponseData<String>> delete(@RequestParam int adoptId) {
        petService.delete(adoptId);

        ResponseData<String> responseData = new ResponseData<>("DELETE_ADOPT_SUCCESS", "Delete pet adoption history successful", null);
        return ResponseEntity.ok(responseData);
    }
}
