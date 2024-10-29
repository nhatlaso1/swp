package org.example.data.response;

import lombok.Data;
import org.example.model.AdoptHistoryVO;

@Data
public class AdoptHistoryResponse {
    private int id;
    private String title;
    private String petName;
    private int petType;
    private int age;
    private String address;
    private String fullName;
    private int type;
    private String image;
    private int status;

    public static AdoptHistoryResponse fromFilterPetVO(AdoptHistoryVO filterPetVO) {
        AdoptHistoryResponse response = new AdoptHistoryResponse();
        response.setId(filterPetVO.getId());
        response.setTitle(filterPetVO.getTitle());
        response.setPetName(filterPetVO.getPetName());
        response.setPetType(filterPetVO.getPetType());
        response.setAge(filterPetVO.getAge());
        response.setAddress(filterPetVO.getAddress());
        response.setFullName(filterPetVO.getFullName());
        response.setStatus(filterPetVO.getStatus());
        response.setType(filterPetVO.getType());
        response.setImage(filterPetVO.getImage());

        return response;
    }
}
