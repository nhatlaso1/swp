package org.example.data.request;

import lombok.Data;

import java.util.List;
@Data
public class CreatePetRequest {
    private int type = 1;
    private String name;
    private int age;
    private String title;
    private List<String> imageUrl;
    private String description;
    private String address;
    private String breed;
}
