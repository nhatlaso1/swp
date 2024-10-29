package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.entities.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetImageRepository extends JpaRepository<PetImage, Integer> {
    @Query(value = "DELETE FROM `pet_images`\n" +
            "WHERE pet_id = :pet_id", nativeQuery = true)
    @Modifying
    @Transactional
   void deleteAllByPetId(@Param("pet_id") int petId);
}
