package com.example.springboot03.service;

import com.example.springboot03.model.dto.request.AttendeeRequest;
import com.example.springboot03.model.entity.Attendee;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    Attendee addAttendee(AttendeeRequest attendeeRequest);

    Attendee getAttendeeById(Integer id);

    Attendee updateAttendeeById(Integer id, AttendeeRequest attendeeRequest);

    Attendee deleteAttendeeById(Integer id);
}
