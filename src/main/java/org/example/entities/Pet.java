package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public List<PetImage> getImages() {
        return images;
    }

    public void setImages(List<PetImage> images) {
        this.images = images;
    }
}
