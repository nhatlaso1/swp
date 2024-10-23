package org.example.repositories;

import jakarta.transaction.Transactional;
import org.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Users v SET v.status = :status WHERE v.id = :id", nativeQuery = true)
    void deactivateUser(@Param("status") String status, @Param("id") Integer id);

    @Query(value = "CALL user_find_with_filter(:full_name, :role_id, :status, :page_size, :page_number)", nativeQuery = true)
    List<User> filter(
            @Param("full_name") String name,
            @Param("role_id") String role,
            @Param("status") String status,
            @Param("page_size") int pageSize,
            @Param("page_number") int pageNumber
    );

    @Query(value = "CALL user_count_with_filter(:full_name, :role_id, :status)", nativeQuery = true)
    int count(
            @Param("full_name") String name,
            @Param("role_id") String role,
            @Param("status") String status
    );
}
