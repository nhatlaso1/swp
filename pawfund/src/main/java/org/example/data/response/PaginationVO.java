package org.example.data.response;

import lombok.Data;

import java.util.List;

@Data
public class PaginationVO<T> {
    private final long totalCount;
    private final List<T> list;

    public PaginationVO( long totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;
    }
}
