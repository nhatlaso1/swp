package org.example.data.response;

import lombok.Data;
import org.example.model.ApplicationVO;

import java.util.List;

@Data
public class ViewAdoptResponse {
    private int id;
    private String title;
    private String petName;
    private int petType;
    private int age;
    private String address;
    private String fullName;
    private String phone;
    private String profileImage;
    private String type;
    private List<String> images;
    private ApplicationVO application;
    private int status;
    private String breed;
    private String description;
}


