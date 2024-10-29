package org.example.data.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateAdoptionRequest {
    private int adoptId;
    private int petTypeId;
    private List<String> images;
    private int age;
    private String title;
    private String description;
    private String address;
    private String petName;
    private String breed;
}
