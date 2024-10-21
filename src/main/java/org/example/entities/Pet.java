package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "breed")
    private String breed;
    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "pet_type_id", nullable = false)
    private PetType petType;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PetImage> images;

    public Pet() {
    }

    public Pet(String name, PetType petType, int age, String breed, String status, String description, String address) {
        this.name = name;
        this.petType = petType;
        this.age = age;
        this.breed = breed;
        this.status = status;
        this.description = description;
        this.address = address;
    }

}
