package org.example.services.impl;

import org.example.data.request.event.CreateEventRequest;
import org.example.entities.Event;
import org.example.entities.User;
import org.example.repositories.EventRepository;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IEventService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public int create(CreateEventRequest request) {
        String username = CommonUtils.getCurrentUsername();
        User currentUser = userRepository.findByUsername(username).get();

        if(currentUser.getRole().getId().equals("USER")){
            return -1;
        }

        Event event = Event.builder()
                .image(request.getImage())
                .description(request.getDescription())
                .targetAmount(request.getTargetAmount())
                .title(request.getTitle())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .createdBy(currentUser)
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(1)
                .currentAmount("0")
                .build();

        eventRepository.save(event);
        return 0;
    }
}
