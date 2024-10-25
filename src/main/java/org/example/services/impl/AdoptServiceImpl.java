package org.example.services.impl;

import org.example.data.request.AdoptHistoryRequest;
import org.example.data.request.CreateAdoptionRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.ViewAdoptResponse;
import org.example.entities.*;
import org.example.enums.AdoptionStatusEnum;
import org.example.model.AdoptHistoryVO;
import org.example.model.ViewAdoptVO;
import org.example.repositories.*;
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
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetImageRepository petImageRepository;

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
        response.setProfileImage(viewAdoptVO.getProfileImage());

        response.setApplication(applicationRepository.findByAdoptId(viewAdoptVO.getId()) == null
                ? null : applicationRepository.findByAdoptId(viewAdoptVO.getId()));

        return response;
    }

    @Override
    public int create(CreateAdoptionRequest request) {
        Pet newPet = new Pet(
                request.getName(),
                new PetType(request.getType()),
                request.getAge(),
                request.getBreed(),
                "Pending",
                request.getDescription(),
                request.getAddress()
        );

        Pet savedPet = petRepository.save(newPet);

        if (request.getImageUrl() != null) {
            for (String imageUrl : request.getImageUrl()) {
                PetImage newPetImage = new PetImage();
                newPetImage.setPet(savedPet);
                newPetImage.setImageUrl(imageUrl);
                newPetImage.setCreateAt(System.currentTimeMillis());

                petImageRepository.save(newPetImage);
            }
        }

        String username = CommonUtils.getCurrentUsername();
        User currentUser = userRepository.findByUsername(username).get();

        Adoption newAdoption = new Adoption();
        newAdoption.setAdopterId(currentUser);
        newAdoption.setPet(savedPet);
        newAdoption.setType(request.getType());
        newAdoption.setStatus(1);
        newAdoption.setTitle(request.getTitle());

        Adoption savedAdopt = adoptionRepository.save(newAdoption);

        Application newApplication = Application.builder()
                .user(currentUser)
                .adoption(savedAdopt)
                .experience(request.getExperience())
                .status(AdoptionStatusEnum.PENDING.getValue())
                .homeOwner(request.getHouseOwner())
                .houseType(request.getHouseType())
                .reason(request.getReason())
                .isAllergic(request.getIsAllergic())
                .build();

        applicationRepository.save(newApplication);

        return 0;
    }
}
