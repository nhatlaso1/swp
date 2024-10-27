package org.example.services.interfaces;

import org.example.data.request.event.CreateEventRequest;

public interface IEventService {
    int create(CreateEventRequest request);
}
