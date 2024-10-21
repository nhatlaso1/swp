package org.example.data.request;

import lombok.Data;

@Data
public class ListUserRequest {
    private String name;
    private String status;
    private String role;
    private int pageSize;
    private int pageNumber;
}
