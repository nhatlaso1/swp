package org.example.repositories;

import org.example.entities.Adoption;
import org.example.model.AdoptHistoryVO;
import org.example.model.ViewAdoptVO;
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

    @Query(value = "SELECT\n" +
            "    a.id AS id,\n" +
            "    a.title AS title,\n" +
            "    p.name AS petName,\n" +
            "    pt.name AS petType,\n" +
            "    p.age AS age,\n" +
            "    p.address AS address,\n" +
            "    u.full_name AS fullName,\n" +
            "    u.image AS profileImage,\n" +
            "    a.type AS type,\n" +
            "    GROUP_CONCAT(pi.image_url SEPARATOR '||') AS images\n" +
            "FROM pets p\n" +
            "JOIN adoptions a ON p.id = a.pet_id\n" +
            "JOIN pet_type pt ON pt.id = p.pet_type_id\n" +
            "JOIN users u ON u.id = a.adopter_id\n" +
            "LEFT JOIN pet_images pi ON pi.pet_id = p.id\n" +
            "WHERE a.id = ?\n" +
            "GROUP BY a.id, p.name, pt.name, p.age, p.address, u.full_name, a.type;\n", nativeQuery = true)
    ViewAdoptVO view(
        int adoptId
    );
}
