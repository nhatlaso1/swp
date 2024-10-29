package org.example.data.request.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DonateEventRequest {
    private int eventId;
    private Double amount;
}
