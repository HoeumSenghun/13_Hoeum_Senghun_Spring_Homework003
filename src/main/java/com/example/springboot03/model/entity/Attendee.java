package com.example.springboot03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
