package org.example.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private User user;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "donation_date")
    private String date;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
