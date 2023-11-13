package com.travel.busyflights.rest;

import com.travel.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travel.busyflights.service.BusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BusyFlights {

    @Autowired private BusyFlightsService busyFlightsService;

    @GetMapping(value = "/busyFlights")
    public ResponseEntity<List<BusyFlightsResponse>> getAllFLights(@RequestBody @Valid BusyFlightsRequest request) {

        List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService.aggregateFlights(request);

        return ResponseEntity.ok(busyFlightsResponse);
    }
}
