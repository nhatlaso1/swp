package org.example.data.response;

import lombok.Data;
import org.example.model.AdoptHistoryVO;

@Data
public class AdoptHistoryResponse {
    private int id;
    private String title;
    private String petName;
    private String petType;
    private int age;
    private String address;
    private String fullName;
    private String type;
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
        if(filterPetVO.getType() == "1"){
            response.setType("Nhận nuôi");
        } else{
            response.setType("Đem cho");
        }
        response.setImage(filterPetVO.getImage());

        return response;
    }
}
