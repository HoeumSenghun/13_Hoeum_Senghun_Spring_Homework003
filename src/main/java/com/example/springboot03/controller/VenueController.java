package com.example.springboot03.controller;

import com.example.springboot03.model.dto.request.VenueRequest;
import com.example.springboot03.model.dto.response.ApiResponse;
import com.example.springboot03.model.entity.Venue;
import com.example.springboot03.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    public final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ApiResponse<List<Venue>> getAllVenue(
            @RequestParam(defaultValue = "1") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit) {

        List<Venue> venues = venueService.getAllVenue(offset, limit);
        return ApiResponse.<List<Venue>>builder()
                .success(true)
                .message("Venues fetched successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping
    public ApiResponse<Venue> addVenue(@RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.addVenue(venueRequest);
        return ApiResponse.<Venue>builder()
                .success(true)
                .message("Venue created successfully")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{venue-id}")
    public ApiResponse<Venue> getVenueById(@PathVariable("venue-id") Integer id) {
        Venue venue = venueService.getVenueById(id);
        return ApiResponse.<Venue>builder()
                .success(true)
                .message("Venue fetched successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/{venue-id}")
    public ApiResponse<Venue> updateVenueById(
            @PathVariable("venue-id") Integer id,
            @RequestBody VenueRequest venueRequest) {

        Venue venue = venueService.updateVenueById(id, venueRequest);
        return ApiResponse.<Venue>builder()
                .success(true)
                .message("Venue updated successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{venue-id}")
    public ApiResponse<Venue> deleteVenueById(@PathVariable("venue-id") Integer id) {
        Venue venue = venueService.deleteVenueById(id);
        return ApiResponse.<Venue>builder()
                .success(true)
                .message("Venue deleted successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
