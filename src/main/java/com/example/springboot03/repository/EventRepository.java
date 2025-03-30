package com.example.springboot03.repository;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
    SELECT e.event_id, e.event_name, e.event_date, e.venue_id, a.attendee_id
    FROM events e
    LEFT JOIN event_attendees ea ON ea.event_id = e.event_id
    LEFT JOIN attendees a ON ea.attendee_id = a.attendee_id
    OFFSET #{page} LIMIT #{limit}
    """)
    @Results(id = "eventsMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.example.springboot03.repository.VenueRepository.getVenueById")),
            @Result(property = "attendeeId", column = "attendee_id",
                    many = @Many(select = "com.example.springboot03.repository.AttendeeRepository.getAttendeeById"))
    })
    List<Event> getAllEvents(Integer page, Integer limit);

    @Select("""
    SELECT * FROM events
    WHERE event_id = #{id}
""")
    @ResultMap("eventsMapper")
    Event getEventById(Integer id);

    @Select("""
    INSERT INTO events (event_name, event_date, venue_id)
    VALUES (#{eventName}, #{eventDate}, #{venueId})
""")
//    @ResultMap("eventsMapper")
    @Options(useGeneratedKeys = true, keyProperty = "eventId")
    Event addEvent(EventRequest eventRequest);
}
