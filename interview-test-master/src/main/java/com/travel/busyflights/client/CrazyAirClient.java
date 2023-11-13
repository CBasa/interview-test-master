package com.travel.busyflights.client;

import com.travel.busyflights.domain.crazyair.CrazyAirRequest;
import com.travel.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public interface CrazyAirClient {

    @RequestMapping(method = RequestMethod.GET, path = "/crazyAirFlights")
    Optional<List<CrazyAirResponse>> getFlights(@RequestBody CrazyAirRequest request);
}
