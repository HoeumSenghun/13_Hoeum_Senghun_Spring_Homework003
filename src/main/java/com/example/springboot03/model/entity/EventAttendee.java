package com.example.springboot03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendee {
    private Integer event_attendee_id;
    private Integer attendee_id;
    private Integer event_id;
}
