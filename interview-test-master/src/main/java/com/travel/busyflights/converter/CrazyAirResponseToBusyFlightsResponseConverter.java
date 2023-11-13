package com.travel.busyflights.converter;

import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travel.busyflights.domain.crazyair.CrazyAirResponse;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyAirResponseToBusyFlightsResponseConverter {

    public BusyFlightsResponse convert(CrazyAirResponse response) {
        return BusyFlightsResponse.builder()
                .airline(response.getAirline())
                .supplier("CrazyAir")
                .fare(getTotalPrice(response.getPrice()))
                .departureAirportCode(response.getDepartureAirportCode())
                .destinationAirportCode(response.getDestinationAirportCode())
                .departureDate(getDateTime(response.getDepartureDate()))
                .arrivalDate(getDateTime(response.getArrivalDate()))
                .build();
    }

    private double getTotalPrice(double price) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(price));
    }

    private String getDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
