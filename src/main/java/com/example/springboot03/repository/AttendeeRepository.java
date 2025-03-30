package com.example.springboot03.repository;

import com.example.springboot03.model.dto.request.AttendeeRequest;
import com.example.springboot03.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Select("""
    SELECT * FROM attendees
    offset #{page} limit #{limit}
""")
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendee> getAllAttendee(Integer page, Integer limit);

    @Select("""
    INSERT INTO attendees (attendee_name, email) 
    VALUES (#{request.attendeeName}, #{request.email})
    RETURNING *
""")
    @ResultMap("attendeeMapper")
    Attendee addAttendee(@Param("request") AttendeeRequest attendeeRequest);

    @Select("""
    SELECT * FROM attendees 
    WHERE attendee_id = #{id}
""")
    @ResultMap("attendeeMapper")
    Attendee getAttendeeById(Integer id);

    @Select("""
    UPDATE attendees
    SET attendee_name=#{request.attendeeName}, email=#{request.email}
    WHERE attendee_id=#{id} 
    RETURNING *
""")
    @ResultMap("attendeeMapper")
    Attendee updateAttendeeById(Integer id,@Param("request") AttendeeRequest attendeeRequest);

    @Select("""
    DELETE FROM attendees
    WHERE attendee_id=#{Id}
    RETURNING *
""")
    @ResultMap("attendeeMapper")
    Attendee deleteAttendeeById(Integer id);
}
