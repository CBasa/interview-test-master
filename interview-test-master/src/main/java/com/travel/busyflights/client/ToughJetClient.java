package com.travel.busyflights.client;

import com.travel.busyflights.domain.crazyair.CrazyAirRequest;
import com.travel.busyflights.domain.crazyair.CrazyAirResponse;
import com.travel.busyflights.domain.toughjet.ToughJetRequest;
import com.travel.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

public interface ToughJetClient {

    @RequestMapping(method = RequestMethod.GET, path = "/toughJetFlights")
    Optional<List<ToughJetResponse>> getFlights(@RequestBody ToughJetRequest request);
}
