package com.example.FlightTicketProject.controller;

import com.example.FlightTicketProject.dto.AirportInfoDTO;
import com.example.FlightTicketProject.mapper.EntityDTOMapper;
import com.example.FlightTicketProject.service.rest.GoflightlabsClientService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Api(tags = "Test airport controller")
@RequestMapping("/api/airports")
@RestController
public class AirportController {

    private final GoflightlabsClientService flightsApiService;

    @GetMapping("/search")
    public ResponseEntity<List<AirportInfoDTO>> getAirportByCity(@RequestParam String city) {
        List<AirportInfoDTO> airportInfoDTO = flightsApiService.findAirportByCity(city).stream()
                .map(EntityDTOMapper::mapExternalApiAirportResponseToAirportInfoDTO)
                .toList();

        return new ResponseEntity<>(airportInfoDTO, HttpStatus.OK);
    }
}