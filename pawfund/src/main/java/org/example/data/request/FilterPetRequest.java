package org.example.data.request;

import lombok.Data;

@Data
public class FilterPetRequest {
    private int type;
    private String address;
    private int petTypeId;
    private int[] age;
}
