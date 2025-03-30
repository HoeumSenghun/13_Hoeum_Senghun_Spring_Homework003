package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.entity.Event;
import com.example.springboot03.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        return eventService.getAllEvents(offset, limit);
    }

    @GetMapping("/{event-id}")
    public Event getEventById(@PathVariable ("event-id") Integer id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody EventRequest eventRequest) {
        return new ResponseEntity<>(eventService.addEvent(eventRequest), HttpStatus.OK);
    }
}
