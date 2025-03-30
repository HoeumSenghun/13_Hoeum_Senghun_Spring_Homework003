package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.AttendeeRequest;
import com.example.springboot03.model.dto.response.ApiResponse;
import com.example.springboot03.model.entity.Attendee;
import com.example.springboot03.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ApiResponse<List<Attendee>> getAllAttendee(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        List<Attendee> attendees = attendeeService.getAllAttendees(offset, limit);
        return ApiResponse.<List<Attendee>>builder()
                .success(true)
                .message("Attendees fetched successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public ApiResponse<Attendee> addAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.addAttendee(attendeeRequest);
        return ApiResponse.<Attendee>builder()
                .success(true)
                .message("Attendee added successfully")
                .status(HttpStatus.CREATED)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{attendee-id}")
    public ApiResponse<Attendee> getAttendeeById(@PathVariable("attendee-id") Integer id) {
        Attendee attendee = attendeeService.getAttendeeById(id);
        return ApiResponse.<Attendee>builder()
                .success(true)
                .message("Attendee fetched successfully")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/{attendee-id}")
    public ApiResponse<Attendee> updateAttendeeById(
            @PathVariable("attendee-id") Integer id,
            @RequestBody AttendeeRequest attendeeRequest) {

        Attendee attendee = attendeeService.updateAttendeeById(id, attendeeRequest);
        return ApiResponse.<Attendee>builder()
                .success(true)
                .message("Attendee updated successfully")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{attendee-id}")
    public ApiResponse<Attendee> deleteAttendeeById(@PathVariable("attendee-id") Integer id) {
        Attendee attendee = attendeeService.deleteAttendeeById(id);
        return ApiResponse.<Attendee>builder()
                .success(true)
                .message("Attendee deleted successfully")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
