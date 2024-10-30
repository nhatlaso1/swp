package org.example.data.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateAdoptionRequest {
    private int type = 2;
    private String title;
    private String name;
    private int age;
    private List<String> imageUrl;
    private String description;
    private String address;
    private String breed;

    private String houseType;
    private int houseOwner;
    private int isAllergic;
    private String experience;
    private String reason;
}
