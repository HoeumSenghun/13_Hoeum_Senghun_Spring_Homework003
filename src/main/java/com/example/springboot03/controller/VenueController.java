package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.VenueRequest;
import com.example.springboot03.model.entity.Venue;
import com.example.springboot03.service.VenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    public final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public List<Venue> getAllVenue(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer Limit) {
        return venueService.getAllVenue(offset, Limit);
    }
    @PostMapping
    public Venue addVenue(@RequestBody VenueRequest venueRequest) {
        return venueService.addVenue(venueRequest);
    }
    @GetMapping("/{venue-id}")
    public Venue getVenueById(@PathVariable("venue-id") Integer id) {
        return venueService.getVenueById(id);
    }
    @PutMapping("/{venue-id}")
    public Venue updateVenueById(@PathVariable("venue-id") Integer id, @RequestBody VenueRequest venueRequest) {
        return venueService.updateVenueById(id, venueRequest);
    }
    @DeleteMapping("/{venue-id}")
    public Venue deleteVenueById(@PathVariable("venue-id") Integer id) {
        return venueService.deleteVenueById(id);
    }
}
