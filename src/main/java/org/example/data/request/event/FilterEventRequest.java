package org.example.data.request.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilterEventRequest {
    private String title;
    private int status;
    private BigDecimal fromTarget;
    private BigDecimal toTarget;
    private int pageSize;
    private int pageNumber;
}
