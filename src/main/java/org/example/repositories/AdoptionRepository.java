package org.example.repositories;

import org.example.entities.Adoption;
import org.example.model.AdoptHistoryVO;
import org.example.model.FilterPetVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Integer> {
    @Query(value = "CALL adopt_find_with_filter(:type, :user_id, :pet_type_id, :age_from, :age_to, :page_size, :page_number)", nativeQuery = true)
    List<AdoptHistoryVO> history(
            @Param("type") int type,
            @Param("user_id") int userId,
            @Param("pet_type_id") int petTypeId,
            @Param("age_from") int ageFrom,
            @Param("age_to") int ageTo,
            @Param("page_size") int pageSize,
            @Param("page_number") int pageNumber
    );

    @Query(value = "CALL adopt_count_with_filter(:type, :user_id, :pet_type_id, :age_from, :age_to)", nativeQuery = true)
    int countHistory(
            @Param("type") int type,
            @Param("user_id") int userId,
            @Param("pet_type_id") int petTypeId,
            @Param("age_from") int ageFrom,
            @Param("age_to") int ageTo
    );
}
