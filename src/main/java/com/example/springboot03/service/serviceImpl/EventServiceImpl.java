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
        Event event = eventRepository.addEvent(eventRequest);

        for (Integer e: eventRequest.getAttendeeId()){
            System.out.println(eventRequest.getAttendeeId());
            System.out.println("attendee id:"+ e);
            System.out.println(eventRequest.getAttendeeId()+"event");
            eventRepository.addEventAttendee(event.getEventId(),e);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Integer id, EventRequest eventRequest) {
        eventRepository.updateEventById(id, eventRequest);

        eventAttendeeRepository.deleteEventAttendeeByEventId(id);

        for (Integer e: eventRequest.getAttendeeId()){
            eventAttendeeRepository.addEventAttendee(id,e);
        }
        return eventRepository.updateEventById(id, eventRequest);
    }


}
