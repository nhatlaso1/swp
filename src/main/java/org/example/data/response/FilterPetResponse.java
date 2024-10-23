package org.example.data.response;

import lombok.Data;
import org.example.model.FilterPetVO;

@Data
public class FilterPetResponse {
    private int id;
    private String title;
    private String petName;
    private String petType;
    private int age;
    private String address;
    private String fullName;
    private String type;
    private String image;
    private Double star;
    private int review;
    private int status;
    private String profileImage;

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
        if(filterPetVO.getType() == "1"){
            response.setType("Nhận nuôi");
        } else{
            response.setType("Đem cho");
        }
        response.setImage(filterPetVO.getImage());
        return response;
    }
}
