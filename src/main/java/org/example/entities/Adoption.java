package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "adoptions")
@Entity
@Data
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    @OneToOne
    @JoinColumn(name = "adopter_id", nullable = false)
    private User adopterId;
    @Column(name = "type")
    private int type;
    @Column(name = "status")
    private int status;
    @Column(name = "adoption_date")
    private String adoptionDate;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "updated_by")
    private int updatedBy;

}
