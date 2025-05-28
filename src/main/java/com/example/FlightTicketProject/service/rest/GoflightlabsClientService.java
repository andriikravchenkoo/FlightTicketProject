package com.example.FlightTicketProject.service.rest;

import com.example.FlightTicketProject.deserializer.GoflightlabsResponseCustomDeserializer;
import com.example.FlightTicketProject.dto.AirportInfoDto;
import com.example.FlightTicketProject.dto.FlightDto;
import com.example.FlightTicketProject.entity.FareClassStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class GoflightlabsClientService {

    private final RestTemplate restTemplate;

    private final GoflightlabsResponseCustomDeserializer customDeserializer;

    public Set<FlightDto> findFlightsByFilter(
            String adults,
            String origin,
            String destination,
            String departureDate,
            String fareClass) {

        log.info(
                "Reading mock flight data from file for: adults = {}, origin = {}, destination = {}, departureDate = {}, fareClass = {}",
                adults, origin, destination, departureDate, fareClass);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mock/flights-response.json")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Mock flight response file not found");
            }

            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            return customDeserializer.flightResponseDeserialize(
                    json, FareClassStatus.valueOf(fareClass.toUpperCase()));

        } catch (IOException e) {
            log.error("Failed to read mock flight data file", e);
            throw new RuntimeException("Unable to fetch flight data", e);
        }
    }


    public List<AirportInfoDto> findAirportByCity(String city) {
        log.info("Reading mock airport data from file for city = {}", city);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mock/airports-response.json")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Mock response file not found");
            }

            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return customDeserializer.airportResponseDeserialize(json);

        } catch (IOException e) {
            log.error("Failed to read mock airport data file", e);
            throw new RuntimeException("Unable to fetch airport data", e);
        }
    }
}
