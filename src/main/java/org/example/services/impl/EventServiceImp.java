package org.example.services.impl;

import org.example.data.request.event.CreateEventRequest;
import org.example.repositories.EventRepository;
import org.example.services.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp implements IEventService {
    @Autowired
    private EventRepository eventRepository;
    @Override
    public int create(CreateEventRequest request) {

        return 0;
    }
}
