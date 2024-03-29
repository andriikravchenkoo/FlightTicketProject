package com.example.FlightTicketProject.service.impl;

import com.example.FlightTicketProject.entity.Flight;
import com.example.FlightTicketProject.exception.ResourceNotFoundException;
import com.example.FlightTicketProject.repository.FlightRepository;
import com.example.FlightTicketProject.security.configuration.JwtAuthenticationFilter;
import com.example.FlightTicketProject.service.FlightService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(String flightId) {
        return flightRepository
                .findById(flightId)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Flight with ID = " + flightId + " will not be found"));
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void cacheAll(Set<Flight> flights) {
        flightRepository.saveAll(flights);
    }

    @Override
    public void update(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public double findPriceFlightByPaymentId(long paymentId) {
        return flightRepository.findPriceFlightByPaymentId(paymentId);
    }

    @Override
    public List<Flight> findAllByUser() {
        return flightRepository.findAllByUser(JwtAuthenticationFilter.getCurrentUserEmail());
    }
}
