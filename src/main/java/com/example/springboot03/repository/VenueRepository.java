package com.example.springboot03.repository;

import com.example.springboot03.model.dto.request.VenueRequest;
import com.example.springboot03.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
    SELECT * FROM venues
    offset #{page} limit #{limit}
""")
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location"),
    })
    List<Venue> getAllVenue(Integer page, Integer limit);

    @Select("""
    INSERT INTO venues(venue_name, location) 
    VALUES (#{request.venueName}, #{request.location})
    RETURNING *
""")
    @ResultMap("venueMapper")
    Venue addVenue(@Param("request") VenueRequest venueRequest);

    @Select("""
    SELECT * FROM venues
    WHERE venue_id = #{id}
""")
    @ResultMap("venueMapper")
    Venue getVenueById(Integer id);

    @Select("""
    UPDATE venues
    SET venue_name = #{request.venueName}, location = #{request.location}
    WHERE venue_id = #{id}
    RETURNING *
""")
    @ResultMap("venueMapper")
    Venue updateVenueById(Integer id,@Param("request") VenueRequest venueRequest);

    @Select("""
    DELETE FROM venues
    WHERE venue_id = #{id}
    RETURNING *
""")
    @ResultMap("venueMapper")
    Venue deleteVenueById(Integer id);
}
