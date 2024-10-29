package org.example.controller;

import org.example.data.request.event.CreateEventRequest;
import org.example.data.request.event.FilterEventRequest;
import org.example.data.request.event.UpdateEventRequest;
import org.example.data.response.PaginationVO;
import org.example.data.response.ResponseData;
import org.example.data.response.event.FilterEventResponse;
import org.example.services.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private IEventService eventService;

    @PostMapping("/event/create")
    public ResponseEntity<ResponseData<String>> create(@RequestBody CreateEventRequest request) {
        int result = eventService.create(request);

        ResponseData<String> responseData = null;
        if(result == -1){
            responseData = new ResponseData<>("DON'T HAVE PERMISSIONS",
                    "You don't have permission.", null);
        } else{
            responseData = new ResponseData<>("CREATE_EVENT_SUCCESS",
                    "Create new event successful", null);
        }

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/public/event/filter")
    public ResponseEntity<ResponseData<PaginationVO<FilterEventResponse>>> filter(@RequestBody FilterEventRequest request) {

        PaginationVO<FilterEventResponse> responsePaginationVO = eventService.filter(request);

        ResponseData<PaginationVO<FilterEventResponse>> responseData = new ResponseData<>("FILTER_EVENT_SUCCESS"
                , "Filter event successful", responsePaginationVO);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/public/event/view")
    public ResponseEntity<ResponseData<FilterEventResponse>> view(@RequestParam int eventId) {

        FilterEventResponse response = eventService.view(eventId);

        ResponseData<FilterEventResponse> responseData = new ResponseData<>("VIEW_EVENT_SUCCESS"
                , "View event detail successful", response);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/event/update")
    public ResponseEntity<ResponseData<Integer>> update(@RequestBody UpdateEventRequest request) {
        ResponseData<Integer> responseData = null;
        int response = eventService.update(request);

        if(response == -1){
            responseData = new ResponseData<>("EVENT_DOESN'T_EXIST"
                    , "Event doesn't exist", response);
            return ResponseEntity.ok(responseData);

        }
        responseData = new ResponseData<>("UPDATE_EVENT_SUCCESS"
                , "Update event successful", response);

        return ResponseEntity.ok(responseData);
    }
}
