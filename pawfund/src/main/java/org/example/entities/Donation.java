package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
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
    private LocalDateTime date;

}
