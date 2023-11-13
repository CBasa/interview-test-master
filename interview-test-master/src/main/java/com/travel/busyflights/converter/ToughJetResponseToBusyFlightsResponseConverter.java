package com.travel.busyflights.converter;

import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travel.busyflights.domain.toughjet.ToughJetResponse;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ToughJetResponseToBusyFlightsResponseConverter {

    public BusyFlightsResponse convert(ToughJetResponse response) {
        return BusyFlightsResponse.builder()
                .airline(response.getCarrier())
                .supplier("ToughJet")
                .fare(getTotalPrice(response.getBasePrice(), response.getTax(), response.getDiscount()))
                .departureAirportCode(response.getDepartureAirportName())
                .destinationAirportCode(response.getArrivalAirportName())
                .departureDate(getDateTime(response.getOutboundDateTime()))
                .arrivalDate(getDateTime(response.getInboundDateTime()))
                .build();
    }

    private double getTotalPrice(double basePrice, double tax, double discount) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(basePrice * (1 - (discount / 100.00)) * (1 + (tax / 100.00))));
    }

    private String getDateTime(String dateTime) {
        return LocalDateTime.ofInstant(Instant.parse(dateTime), ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
