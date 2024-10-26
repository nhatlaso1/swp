package org.example.data.request;

import lombok.Data;

@Data
public class ChangeStatusAdoptRequest {
    private int adoptId;
    private int status;
}
