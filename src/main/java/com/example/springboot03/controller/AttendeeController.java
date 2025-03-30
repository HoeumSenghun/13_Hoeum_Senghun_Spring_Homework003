package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.AttendeeRequest;
import com.example.springboot03.model.entity.Attendee;
import com.example.springboot03.service.AttendeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    final AttendeeService attendeeService;
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public List<Attendee> getAllAttendee(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer Limit) {
        return attendeeService.getAllAttendees(offset, Limit);
    }
    @PostMapping
    public Attendee addAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        return attendeeService.addAttendee(attendeeRequest);
    }
    @GetMapping("/{attendee-id}")
    public Attendee getAttendeeById(@PathVariable("attendee-id") Integer id) {
        return attendeeService.getAttendeeById(id);
    }
    @PutMapping("/{attendee-id}")
    public Attendee updateAttendeeById(@PathVariable("attendee-id") Integer id, @RequestBody AttendeeRequest attendeeRequest) {
        return attendeeService.updateAttendeeById(id, attendeeRequest);
    }
    @DeleteMapping("/{attendee-id}")
    public Attendee deleteAttendeeById(@PathVariable("attendee-id") Integer id) {
        return attendeeService.deleteAttendeeById(id);
    }
}
