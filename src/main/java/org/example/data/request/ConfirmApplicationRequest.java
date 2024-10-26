package org.example.data.request;

import lombok.Data;

@Data
public class ConfirmApplicationRequest {
    private int applicationId;
    private int status;
}
