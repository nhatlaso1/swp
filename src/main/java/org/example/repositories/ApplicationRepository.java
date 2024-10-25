package org.example.repositories;

import org.example.entities.Application;
import org.example.model.ApplicationVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query(value = "SELECT id AS id,\n" +
            "user_id AS userId,\n" +
            "adopt_id AS adoptId,\n" +
            "house_type AS houseType,\n" +
            "is_allergic AS isAllregic,\n" +
            "experience AS experience,\n" +
            "reason AS reason,\n" +
            "home_owner AS homeOwner,\n" +
            "status AS status\n" +
            "FROM application where adopt_id = ?;", nativeQuery = true)
    ApplicationVO findByAdoptId(int adoptId);
}
