package com.travel.busyflights.service;

import com.travel.busyflights.client.CrazyAirClient;
import com.travel.busyflights.client.ToughJetClient;
import com.travel.busyflights.converter.CrazyAirResponseToBusyFlightsResponseConverter;
import com.travel.busyflights.converter.ToughJetResponseToBusyFlightsResponseConverter;
import com.travel.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travel.busyflights.domain.crazyair.CrazyAirRequest;
import com.travel.busyflights.domain.crazyair.CrazyAirResponse;
import com.travel.busyflights.domain.toughjet.ToughJetRequest;
import com.travel.busyflights.domain.toughjet.ToughJetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusyFlightsService {

    private CrazyAirClient crazyAirClient;
    private ToughJetClient toughJetClient;
    private CrazyAirResponseToBusyFlightsResponseConverter crazyAirConverter;
    private ToughJetResponseToBusyFlightsResponseConverter toughJetConverter;


    public List<BusyFlightsResponse> aggregateFlights(BusyFlightsRequest request) {
        ArrayList<BusyFlightsResponse> flightsResponses = new ArrayList<>();
        ArrayList<CrazyAirResponse> crazyAirFLights = getCrazyAirFLights(request);


        crazyAirFLights.stream().forEach(x -> flightsResponses.add(crazyAirConverter.convert(x)));
        getToughJetFLights(request).stream().forEach(x -> flightsResponses.add(toughJetConverter.convert(x)));

        List<BusyFlightsResponse> sortedResults = flightsResponses.stream().sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());


        return sortedResults;
    }

    private ArrayList<CrazyAirResponse> getCrazyAirFLights(BusyFlightsRequest request) {
        ArrayList<CrazyAirResponse> results = new ArrayList<>();

        CrazyAirRequest crazyAirRequest = CrazyAirRequest.builder()
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .passengerCount(request.getNumberOfPassengers())
                .build();

        try {
            Optional<List<CrazyAirResponse>> crazyAirFlights = crazyAirClient.getFlights(crazyAirRequest);
            if (crazyAirFlights.isPresent()) {
                crazyAirFlights.get().stream().forEach(x -> results.add(x));
            }
        } catch (Exception e) {
            log.error("Error retrieving flights from Crazy Air: ", e);
        }
        return results;
    }

    private ArrayList<ToughJetResponse> getToughJetFLights(BusyFlightsRequest request) {
        ArrayList<ToughJetResponse> results = new ArrayList<>();

        ToughJetRequest toughJetRequest = ToughJetRequest.builder()
                .from(request.getOrigin())
                .to(request.getDestination())
                .outboundDate(request.getDepartureDate())
                .inboundDate(request.getReturnDate())
                .numberOfAdults(request.getNumberOfPassengers())
                .build();

        try {
            Optional<List<ToughJetResponse>> toughJetFlights = toughJetClient.getFlights(toughJetRequest);
            if (toughJetFlights.isPresent()) {
                toughJetFlights.get().stream().forEach(x -> results.add(x));
            }
        } catch (Exception e) {
            log.error("Error retrieving flights from Tough Jet: ", e);
        }
        return results;
    }




}
