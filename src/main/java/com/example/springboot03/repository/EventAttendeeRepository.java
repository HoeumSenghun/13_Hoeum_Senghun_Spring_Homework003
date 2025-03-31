package com.example.springboot03.repository;

import org.apache.ibatis.annotations.*;

@Mapper
public interface EventAttendeeRepository {
    @Select("""
    INSERT INTO event_attendee (event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})

""")
    @Results(id = "eaMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "attendeeId", column = "attendee_id")
    })
    void addEventAttendee( Integer eventId, Integer attendeeId);

    @Select("""
    DELETE FROM event_attendee
    WHERE event_id = #{id}
    
""")
    @ResultMap("eaMapper")
    void deleteEventAttendeeByEventId(Integer id);
}
