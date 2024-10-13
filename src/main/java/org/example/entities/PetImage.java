package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pet_images")
public class PetImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "created_at")
    private Long createAt;

    public PetImage() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Long getCreate() {
        return createAt;
    }

    public void setCreate(Long create) {
        this.createAt = create;
    }
}
