package org.example.data.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateAdoptionRequest {
    private int adoptId;
    private int petId;
    private int type;
    private List<String> images;
    private int age;
    private String title;
    private String description;
    private String address;
    private String breed;

    public int getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(int adoptId) {
        this.adoptId = adoptId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
