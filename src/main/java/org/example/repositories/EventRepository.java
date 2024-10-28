package org.example.repositories;

import org.example.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "CALL event_find_with_filter(:title, :status, :from_target, :to_target, :page_size, :page_number)", nativeQuery = true)
    List<Event> filter(
            @Param("title") String title,
            @Param("status") int status,
            @Param("from_target") BigDecimal fromTarget,
            @Param("to_target") BigDecimal toTarget,
            @Param("page_size") int pageSize,
            @Param("page_number") int pageNumber
    );

    @Query(value = "CALL event_count_with_filter(:title, :status, :from_target, :to_target)", nativeQuery = true)
    int count(
            @Param("title") String title,
            @Param("status") int status,
            @Param("from_target") BigDecimal fromTarget,
            @Param("to_target") BigDecimal toTarget
    );
}
