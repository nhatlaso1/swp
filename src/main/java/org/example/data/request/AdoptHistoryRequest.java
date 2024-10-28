package org.example.data.request;

import lombok.Data;

@Data
public class AdoptHistoryRequest {
    private int type;
    private int petTypeId;
    private int ageFrom;
    private int ageTo;
    private int pageSize;
    private int pageNumber;

}
