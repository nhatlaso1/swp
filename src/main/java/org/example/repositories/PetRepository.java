package org.example.repositories;

import org.example.entities.Pet;
import org.example.model.FilterPetVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query(value = "CALL pet_find_with_filter(:type, :address, :pet_type_id, :age_from, :age_to, :page_size, :page_number)", nativeQuery = true)
    List<FilterPetVO> findPetsWithFilter(
            @Param("type") int type,
            @Param("address") String address,
            @Param("pet_type_id") int petTypeId,
            @Param("age_from") int ageFrom,
            @Param("age_to") int ageTo,
            @Param("page_size") int pageSize,
            @Param("page_number") int pageNumber
    );

    @Query(value = "CALL pet_count_with_filter(:type, :address, :pet_type_id, :age_from, :age_to)", nativeQuery = true)
    int countPetsWithFilter(
            @Param("type") int type,
            @Param("address") String address,
            @Param("pet_type_id") int petTypeId,
            @Param("age_from") int ageFrom,
            @Param("age_to") int ageTo
    );
}
