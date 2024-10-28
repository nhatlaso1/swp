package org.example.data.response.event;

import lombok.Data;
import org.example.data.response.AdoptHistoryResponse;
import org.example.entities.Event;
import org.example.model.AdoptHistoryVO;

import java.math.BigDecimal;

@Data
public class FilterEventResponse {
    private int id;
    private String title;
    private String image;
    private String description;
    private String startDate;
    private String endDate;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private int status;
    private String createdDate;

    public static FilterEventResponse fromFilterEventList(Event event) {
        FilterEventResponse response = new FilterEventResponse();
        response.setId(event.getId());
        response.setTitle(event.getTitle());
        response.setCreatedDate(event.getCreatedDate());
        response.setDescription(event.getDescription());
        response.setStatus(event.getStatus());
        response.setCurrentAmount(event.getCurrentAmount());
        response.setTargetAmount(event.getTargetAmount());
        response.setStatus(event.getStatus());
        response.setImage(event.getImage());
        response.setStartDate(event.getStartDate());
        response.setEndDate(event.getEndDate());

        return response;
    }
}
