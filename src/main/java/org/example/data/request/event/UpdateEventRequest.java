package org.example.data.request.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateEventRequest {
    private int id;
    private String title;
    private String image;
    private String description;
    private String startDate;
    private String endDate;
    private BigDecimal targetAmount;
}
