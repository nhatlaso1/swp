package org.example.services.impl;

import org.example.data.request.CreateAdoptionRequest;
import org.example.data.request.CreatePetRequest;
import org.example.data.request.FilterPetRequest;
import org.example.data.request.UpdateAdoptionRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.FilterPetResponse;
import org.example.data.response.PaginationVO;
import org.example.entities.*;
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
    public int create(CreatePetRequest request) {
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

        adoptionRepository.save(newAdoption);

        return 1;
    }

    @Override
    public int update(UpdateAdoptionRequest request) {
        Adoption existingAdoption = adoptionRepository.findById(request.getAdoptId())
                .orElseThrow(() -> new RuntimeException("Adoption not found with ID: " + request.getAdoptId()));

        Pet pet = null;
        if (existingAdoption.getPet().getId() != 0) {
            pet = petRepository.findById(existingAdoption.getPet().getId())
                    .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + existingAdoption.getPet().getId()));

            pet.setAge(request.getAge());
            pet.setBreed(request.getBreed());
            pet.setPetType(new PetType(request.getType()));
            pet.setDescription(request.getDescription());

            existingAdoption.setPet(pet);
        }

        existingAdoption.setTitle(request.getTitle());
        existingAdoption.setType(request.getType());
        existingAdoption.setPet(pet);

        adoptionRepository.save(existingAdoption);

        return 1;
    }

    @Override
    public int delete(int adoptId) {
        adoptionRepository.deleteById(adoptId);
        return 0;
    }

}
