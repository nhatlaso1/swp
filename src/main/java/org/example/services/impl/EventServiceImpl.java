package org.example.services.impl;

import org.example.data.request.event.CreateEventRequest;
import org.example.data.request.event.DonateEventRequest;
import org.example.data.request.event.FilterEventRequest;
import org.example.data.request.event.UpdateEventRequest;
import org.example.data.response.AdoptHistoryResponse;
import org.example.data.response.PaginationVO;
import org.example.data.response.event.FilterEventResponse;
import org.example.entities.Donation;
import org.example.entities.Event;
import org.example.entities.User;
import org.example.repositories.DonationRepository;
import org.example.repositories.EventRepository;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IEventService;
import org.example.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DonationRepository donationRepository;

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
                .currentAmount(BigDecimal.valueOf(0))
                .build();

        eventRepository.save(event);
        return 0;
    }

    @Override
    public PaginationVO<FilterEventResponse> filter(FilterEventRequest request) {

        List<Event> eventList = eventRepository.filter(
                request.getTitle(),
                request.getStatus(),
                request.getFromTarget(),
                request.getToTarget(),
                request.getPageSize(),
                request.getPageNumber()
        );

        int count = eventRepository.count(
                request.getTitle(),
                request.getStatus(),
                request.getFromTarget(),
                request.getToTarget()
        );

        List<FilterEventResponse> eventResponses = eventList.stream()
                .map(FilterEventResponse::fromFilterEventList)
                .collect(Collectors.toList());

        PaginationVO<FilterEventResponse> responsePaginationVO = new PaginationVO<>(
                count, eventResponses
        );

        return responsePaginationVO;
    }

    @Override
    public FilterEventResponse view(int eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));

        FilterEventResponse response = FilterEventResponse.fromFilterEventList(event);

        return response;
    }

    @Override
    public int update(UpdateEventRequest request) {
        Event event = eventRepository.findById(request.getId()).orElse(null);

        if(event == null){
            return -1;
        }

        event.setImage(request.getImage());
        event.setDescription(request.getDescription());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setTargetAmount(request.getTargetAmount());
        event.setTitle(request.getTitle());

        eventRepository.save(event);
        return 0;
    }

    @Override
    public int donate(DonateEventRequest request) {
        String username = CommonUtils.getCurrentUsername();
        User currentUser = userRepository.findByUsername(username).get();
        Event event = eventRepository.findById(request.getEventId()).orElse(null);

        if(event != null && event.getCurrentAmount().compareTo(event.getTargetAmount()) <= 0){
            Donation donation = Donation.builder()
                    .event(event)
                    .amount(request.getAmount())
                    .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .user(currentUser)
                    .build();

            donationRepository.save(donation);

            event.setCurrentAmount(event.getCurrentAmount().add(BigDecimal.valueOf(request.getAmount())));
            if(event.getCurrentAmount().add(BigDecimal.valueOf(request.getAmount())).compareTo(event.getTargetAmount()) >= 0){
                event.setStatus(2);
            } else{
                event.setStatus(3);
            }
            eventRepository.save(event);
            return 0;
        }
        return -1;
    }

}
