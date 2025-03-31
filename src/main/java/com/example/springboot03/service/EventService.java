package com.example.springboot03.service;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer offset, Integer limit);

    Event getEventById(Integer id);

    Event addEvent(EventRequest eventRequest);

    Event updateEventById(Integer id, EventRequest eventRequest);
}
