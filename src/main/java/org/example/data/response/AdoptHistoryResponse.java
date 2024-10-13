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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AdoptHistoryResponse() {
    }

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
