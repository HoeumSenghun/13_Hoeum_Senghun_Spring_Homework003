package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.dto.response.ApiResponse;
import com.example.springboot03.model.entity.Event;
import com.example.springboot03.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ApiResponse<List<Event>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        List<Event> events = eventService.getAllEvents(offset, limit);
        return ApiResponse.<List<Event>>builder()
                .success(true)
                .message("Events fetched successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{event-id}")
    public ApiResponse<Event> getEventById(@PathVariable("event-id") Integer id) {
        Event event = eventService.getEventById(id);
        return ApiResponse.<Event>builder()
                .success(true)
                .message("Event fetched successfully")
                .status(HttpStatus.OK)
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public ApiResponse<Event> addEvent(@RequestBody EventRequest eventRequest) {
        Event event = eventService.addEvent(eventRequest);
        return ApiResponse.<Event>builder()
                .success(true)
                .message("Event added successfully")
                .status(HttpStatus.CREATED)
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/{event-id}")
    public ApiResponse<Event> updateEventById(@PathVariable ("event-id") Integer id, @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEventById(id, eventRequest);
        return ApiResponse.<Event>builder()
                .success(true)
                .message("Event Update successfullly")
                .status(HttpStatus.OK)
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
    }



}
