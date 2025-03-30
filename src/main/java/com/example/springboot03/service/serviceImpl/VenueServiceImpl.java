package com.example.springboot03.service.serviceImpl;

import com.example.springboot03.exception.BadRequestException;
import com.example.springboot03.exception.NotFoundException;
import com.example.springboot03.model.dto.request.VenueRequest;
import com.example.springboot03.model.entity.Venue;
import com.example.springboot03.repository.VenueRepository;
import com.example.springboot03.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenue(Integer offset, Integer limit) {
        Integer page = (offset - 1) * limit;
        return venueRepository.getAllVenue(page, limit);
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        if (venueRequest == null || venueRequest.getVenueName().isEmpty()) {
            throw new BadRequestException("Invalid venue data");
        }
        return venueRepository.addVenue(venueRequest);
    }

    @Override
    public Venue getVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue not found with ID: " + id);
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(Integer id, VenueRequest venueRequest) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue not found with ID: " + id);
        }
        return venueRepository.updateVenueById(id, venueRequest);
    }

    @Override
    public Venue deleteVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue not found with ID: " + id);
        }
        return venueRepository.deleteVenueById(id);
    }
}
