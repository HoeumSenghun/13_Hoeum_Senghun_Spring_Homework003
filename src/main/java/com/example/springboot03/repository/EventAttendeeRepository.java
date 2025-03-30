package com.example.springboot03.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface EventAttendeeRepository {
    @Insert("""
    INSERT INTO event_attendee (event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
""")
    @Results(id = "EAMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "attendeeId", column = "attendee_id")
    })
    void addEventAttendee(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);
}
