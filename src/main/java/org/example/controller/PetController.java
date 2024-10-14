package org.example.controller;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.request.FilterPetRequest;
import org.example.data.response.DropdownPetTypeResponse;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ResponseData;
import org.example.entities.PetType;
import org.example.model.FilterPetVO;
import org.example.repositories.PetTypeRepository;
import org.example.services.interfaces.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private IPetService petService;
    @Autowired
    private PetTypeRepository petTypeRepository;

    @PostMapping("/public/pet/filter")
    public ResponseEntity<ResponseData<PaginationVO<FilterPetResponse>>> filter(@RequestBody FilterPetRequest request) {

        PaginationVO<FilterPetResponse> responsePaginationVO = petService.filter(request);

        ResponseData<PaginationVO<FilterPetResponse>> responseData = new ResponseData<>("FILTER_PET_SUCCESS", "Filter pet successful", responsePaginationVO);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/public/pet/type/dropdown")
    public ResponseEntity<ResponseData<List<PetType>>> dropdownPetType() {

        List<PetType> responsePagination = petTypeRepository.findAll();

        ResponseData<List<PetType>> responseData = new ResponseData<>("DROPDOWN_PET_TYPE_SUCCESS",
                "Dropdown pet type successful", responsePagination);
        return ResponseEntity.ok(responseData);
    }

}
