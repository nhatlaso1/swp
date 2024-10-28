package org.example.data.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateAdoptionRequest {
    private int type = 2;
    private String title;
    private int petId;

    private String houseType;
    private int houseOwner;
    private int isAllergic;
    private String experience;
    private String reason;
}
