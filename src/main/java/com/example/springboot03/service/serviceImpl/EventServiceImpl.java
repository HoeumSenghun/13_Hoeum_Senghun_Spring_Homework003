package com.example.springboot03.service.serviceImpl;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.entity.Event;
import com.example.springboot03.repository.EventAttendeeRepository;
import com.example.springboot03.repository.EventRepository;
import com.example.springboot03.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    public final EventRepository eventRepository;
    public final EventAttendeeRepository eventAttendeeRepository;
    public EventServiceImpl(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        Integer page = (offset - 1) * limit;
        return eventRepository.getAllEvents(page, limit);
    }

    @Override
    public Event getEventById(Integer id) {
        return eventRepository.getEventById(id);
    }

    @Override
    public Event addEvent(EventRequest eventRequest) {
        // Map EventRequest to Event entity
        Event event = new Event();
        event.setEventName(eventRequest.getEventName());
        event.setEventDate(eventRequest.getEventDate());

        // Insert the event into the database and retrieve the generated eventId
        event = eventRepository.addEvent(eventRequest);  // MyBatis will populate eventId here

        // Handle attendee mapping if applicable
        if (eventRequest.getAttendeeId() != null && !eventRequest.getAttendeeId().isEmpty()) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                eventAttendeeRepository.addEventAttendee(event.getEventId(), attendeeId);
            }
        }

        return event;  // Return the event object with eventId set
    }

}
