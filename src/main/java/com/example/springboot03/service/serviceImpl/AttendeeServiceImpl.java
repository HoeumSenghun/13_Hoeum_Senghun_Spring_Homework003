package com.example.springboot03.service.serviceImpl;

import com.example.springboot03.exception.NotFoundException;
import com.example.springboot03.model.dto.request.AttendeeRequest;
import com.example.springboot03.model.entity.Attendee;
import com.example.springboot03.repository.AttendeeRepository;
import com.example.springboot03.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer limit) {
        Integer page = (offset - 1) * limit;
        return attendeeRepository.getAllAttendees(page, limit);
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        if (attendeeRequest == null || attendeeRequest.getName().isEmpty()) {
            throw new BadRequestException("Invalid attendee data");
        }
        return attendeeRepository.addAttendee(attendeeRequest);
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with ID: " + id);
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(Integer id, AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with ID: " + id);
        }
        return attendeeRepository.updateAttendeeById(id, attendeeRequest);
    }

    @Override
    public Attendee deleteAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee not found with ID: " + id);
        }
        return attendeeRepository.deleteAttendeeById(id);
    }
}
