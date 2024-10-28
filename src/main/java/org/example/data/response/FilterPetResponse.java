package org.example.data.response;

import lombok.Data;
import org.example.model.FilterPetVO;

@Data
public class FilterPetResponse {
    private int id;
    private String title;
    private String petName;
    private int petType;
    private int age;
    private String address;
    private String fullName;
    private int type;
    private String image;
    private String breed;
    private Double star;
    private int review;
    private int status;
    private String profileImage;
    private String description;

    public static FilterPetResponse fromFilterPetVO(FilterPetVO filterPetVO) {
        FilterPetResponse response = new FilterPetResponse();
        response.setId(filterPetVO.getId());
        response.setTitle(filterPetVO.getTitle());
        response.setPetName(filterPetVO.getPetName());
        response.setPetType(filterPetVO.getPetType());
        response.setAge(filterPetVO.getAge());
        response.setAddress(filterPetVO.getAddress());
        response.setFullName(filterPetVO.getFullName());
        response.setStatus(filterPetVO.getStatus());
        response.setProfileImage(filterPetVO.getProfileImage());
        response.setStar(filterPetVO.getAverageStar());
        response.setReview(filterPetVO.getReviewCount());
        response.setType(filterPetVO.getType());
        response.setDescription(filterPetVO.getDescription());
        response.setBreed(filterPetVO.getBreed());
        response.setImage(filterPetVO.getImage());
        return response;
    }
}
