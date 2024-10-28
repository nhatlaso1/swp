package org.example.controller;

import org.example.data.request.event.CreateEventRequest;
import org.example.data.response.ResponseData;
import org.example.services.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
