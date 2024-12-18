package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "application")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "adopt_id", nullable = false)
    private Adoption adoption;
    @Column(name = "house_type")
    private String houseType;
    @Column(name = "home_owner")
    private int homeOwner;
    @Column(name = "is_allergic")
    private int isAllergic;
    @Column(name = "experience")
    private String experience;
    @Column(name = "reason")
    private String reason;
    @Column(name = "status")
    private int status;
    @Column(name = "updated_at")
    private String updatedAt;
    @Column(name = "updated_by")
    private int updatedBy;
}
