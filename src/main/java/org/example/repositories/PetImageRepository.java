package org.example.repositories;

import org.example.data.request.CreateAdoptionRequest;
import org.example.entities.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PetImageRepository extends JpaRepository<PetImage, Integer> {
    void create(CreateAdoptionRequest request);
}
