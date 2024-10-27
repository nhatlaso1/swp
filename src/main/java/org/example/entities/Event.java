package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.example.entities.User;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "target_amount")
    private String targetAmount;

    @Column(name = "current_amount")
    private String currentAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private String createdDate;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
