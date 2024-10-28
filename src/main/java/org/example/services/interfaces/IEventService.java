package org.example.services.interfaces;

import org.example.data.request.event.CreateEventRequest;
import org.example.data.request.event.FilterEventRequest;
import org.example.data.response.PaginationVO;
import org.example.data.response.event.FilterEventResponse;

public interface IEventService {
    int create(CreateEventRequest request);
    PaginationVO<FilterEventResponse> filter(FilterEventRequest request);
}
