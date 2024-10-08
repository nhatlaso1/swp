package org.example.services.impl;

import org.example.data.request.AdoptHistoryRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ViewAdoptResponse;
import org.example.entities.User;
import org.example.model.AdoptHistoryVO;
import org.example.model.ViewAdoptVO;
import org.example.repositories.AdoptionRepository;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IAdoptService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptServiceImpl implements IAdoptService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PaginationVO<AdoptHistoryResponse> history(AdoptHistoryRequest request) {

        User currentUser = userRepository.findByUsername(CommonUtils.getCurrentUsername()).get();

        List<AdoptHistoryVO> petVOS = adoptionRepository.history(
                request.getType(),
                currentUser.getId(),
                request.getPetTypeId(),
                request.getAgeFrom(),
                request.getAgeTo(),
                request.getPageSize(),
                request.getPageNumber()
        );

        int count = adoptionRepository.countHistory(
                request.getType(),
                currentUser.getId(),
                request.getPetTypeId(),
                request.getAgeFrom(),
                request.getAgeTo()
        );

        List<AdoptHistoryResponse> petResponses = petVOS.stream()
                .map(AdoptHistoryResponse::fromFilterPetVO)
                .collect(Collectors.toList());

        PaginationVO<AdoptHistoryResponse> responsePaginationVO = new PaginationVO<>(
                count, petResponses
        );

        return responsePaginationVO;
    }

    @Override
    public ViewAdoptResponse view(int adoptId) {
        ViewAdoptVO viewAdoptVO = adoptionRepository.view(adoptId);

        List<String> imageList = Arrays.asList(viewAdoptVO.getImages().split("\\|\\|"));

        ViewAdoptResponse response = new ViewAdoptResponse();
        response.setId(viewAdoptVO.getId());
        response.setTitle(viewAdoptVO.getTitle());
        response.setPetName(viewAdoptVO.getPetName());
        response.setPetType(viewAdoptVO.getPetType());
        response.setAge(viewAdoptVO.getAge());
        response.setAddress(viewAdoptVO.getAddress());
        response.setFullName(viewAdoptVO.getFullName());
        response.setType(viewAdoptVO.getType());
        response.setImages(imageList);

        return response;
    }
}
