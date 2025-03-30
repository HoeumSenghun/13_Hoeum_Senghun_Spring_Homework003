package com.example.springboot03.service;

import com.example.springboot03.model.dto.request.VenueRequest;
import com.example.springboot03.model.entity.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue(Integer offset, Integer limit);

    Venue addVenue(VenueRequest venueRequest);

    Venue getVenueById(Integer id);

    Venue updateVenueById(Integer id, VenueRequest venueRequest);

    Venue deleteVenueById(Integer id);
}
