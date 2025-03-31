package com.example.springboot03.repository;

import com.example.springboot03.model.dto.request.EventRequest;
import com.example.springboot03.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
    SELECT * FROM events
    OFFSET #{page} LIMIT #{limit}
    """)
    @Results(id = "eventsMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.example.springboot03.repository.VenueRepository.getVenueById")),
            @Result(property = "attendee", column = "event_id",
                    many = @Many(select = "com.example.springboot03.repository.AttendeeRepository.getAllEventAttendee"))
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
    VALUES (#{request.eventName}, #{request.eventDate}, #{request.venueId})
    RETURNING *
""")
    @ResultMap("eventsMapper")
    Event addEvent(@Param("request") EventRequest eventRequest);

    @Select("""
    INSERT INTO event_attendee (event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
""")
    void addEventAttendee(Integer eventId, Integer attendeeId);

    @Select("""
    UPDATE events
    SET event_name = #{request.eventName},
        event_date = #{request.eventDate},
        venue_id = #{request.venueId}
    WHERE event_id = #{id}
    RETURNING *
""")
    @ResultMap("eventsMapper")
    @Result(property = "venueId", column = "venue_id")
    Event updateEventById(Integer id, @Param("request") EventRequest eventRequest);
}
