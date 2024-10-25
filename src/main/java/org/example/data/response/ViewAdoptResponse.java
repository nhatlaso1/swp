package org.example.data.response;

import lombok.Data;
import org.example.model.ApplicationVO;

import java.util.List;

@Data
public class ViewAdoptResponse {
    private int id;
    private String title;
    private String petName;
    private String petType;
    private int age;
    private String address;
    private String fullName;
    private String profileImage;
    private String type;
    private List<String> images;
    private ApplicationVO application;
}


