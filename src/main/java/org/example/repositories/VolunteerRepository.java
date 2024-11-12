package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.entities.Volunteer;
import org.example.model.VolunteerDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    @Query(value = "SELECT u.email as email,\n" +
            "\t\tu.full_name as fullName,\n" +
            "        u.phone as phone,\n" +
            "        u.address as address,\n" +
            "        v.status as status,\n" +
            "        v.dob as dob,\n" +
            "        v.cccd as cccd,\n" +
            "        v.experience,\n" +
            "        v.current_job as currentJob,\n" +
            "        v.gender as gender,\n" +
            "        v.reason as reason\n" +
            "        FROM volunteers v JOIN users u\n" +
            "ON v.user_id = u.id where user_id = ?", nativeQuery = true)
    VolunteerDetailVO findByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE volunteers v SET v.status = :status WHERE v.user_id = :id", nativeQuery = true)
    void confirmStatus(@Param("status") String status, @Param("id") Integer id);


}
