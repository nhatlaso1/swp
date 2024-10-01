package org.example.data.request;

import lombok.Data;

@Data
public class CreateAdoptionRequest {
    private String title;
    private String imageUrl;
    private int petType;
}
