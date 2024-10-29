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
import org.example.repositories.*;
import org.example.services.interfaces.IPetService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private PetTypeRepository petTypeRepository;


    @Override
    public PaginationVO<FilterPetResponse> filter(FilterPetRequest request) {

        List<FilterPetVO> petVOS = petRepository.findPetsWithFilter(
                request.getType(),
                request.getAddress() == null ? "" : request.getAddress(),
                request.getPetTypeId(),
                request.getAgeFrom(),
                request.getAgeTo(),
                request.getStatus(),
                request.getPageSize(),
                request.getPageNumber()
        );

        int count = petRepository.countPetsWithFilter(
                request.getType(),
                request.getAddress() == null ? "" : request.getAddress(),
                request.getPetTypeId(),
                request.getStatus(),
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
                new PetType(request.getPetTypeId()),
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
        String username = CommonUtils.getCurrentUsername();
        User currentUser = userRepository.findByUsername(username).get();

        Adoption existingAdoption = adoptionRepository.findById(request.getAdoptId())
                .orElseThrow(() -> new RuntimeException("Adoption not found with ID: " + request.getAdoptId()));

        Pet pet;
        if (existingAdoption.getPet().getId() != 0) {
            pet = petRepository.findById(existingAdoption.getPet().getId())
                    .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + existingAdoption.getPet().getId()));

            pet.setAge(request.getAge());
            pet.setBreed(request.getBreed());
            PetType petType = petTypeRepository.findById(request.getPetTypeId()).orElseThrow();
            pet.setPetType(petType);
            pet.setDescription(request.getDescription());

            petImageRepository.deleteAllByPetId(pet.getId());

            List<PetImage> newPetImages = request.getImages().stream()
                    .map(imageUrl -> {
                        PetImage petImage = new PetImage();
                        petImage.setImageUrl(imageUrl);
                        petImage.setPet(pet);
                        return petImage;
                    }).collect(Collectors.toList());

            petImageRepository.saveAll(newPetImages);

            existingAdoption.setPet(pet);
        } else {
            pet = null;
        }

        existingAdoption.setTitle(request.getTitle());
        existingAdoption.setUpdatedBy(currentUser.getId());
        existingAdoption.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        existingAdoption.setPet(pet);

        adoptionRepository.save(existingAdoption);

        return 1;
    }


    @Override
    public int delete(int adoptId) {
        Adoption adoption = adoptionRepository.findById(adoptId).orElse(null);

        if (adoption == null) {
            return -1;
        }

        adoptionRepository.deleteById(adoptId);
        return 0;
    }


}
