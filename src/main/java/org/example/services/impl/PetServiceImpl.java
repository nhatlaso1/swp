package org.example.services.impl;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.request.FilterPetRequest;
import org.example.data.request.UpdateAdoptionRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;
import org.example.entities.Adoption;
import org.example.entities.Pet;
import org.example.entities.PetImage;
import org.example.entities.User;
import org.example.model.FilterPetVO;
import org.example.repositories.AdoptionRepository;
import org.example.repositories.PetImageRepository;
import org.example.repositories.PetRepository;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IPetService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements IPetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private UserRepository userRepository;


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

    @Override
    public int create(CreateAdoptionRequest request) {
        Pet newPet = new Pet(
                request.getName(),
                request.getAge(),
                request.getBreed(),
                "Pending"
        );

        Pet savedPet = petRepository.save(newPet);

        if (request.getImageUrl() != null) {
            for (String imageUrl : request.getImageUrl()) {
                PetImage newPetImage = new PetImage();
                newPetImage.setPet(savedPet);
                newPetImage.setImageUrl(imageUrl);

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

        adoptionRepository.save(newAdoption);

        return 1;
    }

    @Override
    public int update(UpdateAdoptionRequest request) {
        return 0;
    }

    @Override
    public PaginationVO<AdoptHistoryResponse> history() {
        return null;
    }


}
