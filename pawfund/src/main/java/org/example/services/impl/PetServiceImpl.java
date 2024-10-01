package org.example.services.impl;

import org.example.data.request.FilterPetRequest;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;
import org.example.model.FilterPetVO;
import org.example.repositories.PetRepository;
import org.example.services.interfaces.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements IPetService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public PaginationVO<FilterPetResponse> filter(FilterPetRequest request) {

        List<FilterPetVO> petVOS = petRepository.findPetsWithFilter(
                request.getType(),
                request.getAddress() == null ? "" : request.getAddress(),
                request.getType(),
                request.getAgeFrom(),
                request.getAgeTo(),
                request.getPageSize(),
                request.getPageNumber()
        );

        int count = petRepository.countPetsWithFilter(
                request.getType(),
                request.getAddress() == null ? "" : request.getAddress(),
                request.getType(),
                request.getAgeFrom(),
                request.getAgeTo()
        );

        List<FilterPetResponse> petResponses = petVOS.stream()
                .map(FilterPetResponse::fromFilterPetVO)
                .collect(Collectors.toList());

        PaginationVO<FilterPetResponse> responsePaginationVO = new PaginationVO<>(
                count, petResponses
        );

        return responsePaginationVO;
    }


}
