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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BusyFlightsServiceTest {

	@InjectMocks private BusyFlightsService service;
	@Mock
	private CrazyAirClient crazyAirClient;
	@Mock
	private ToughJetClient toughJetClient;
	@Mock
	private CrazyAirResponseToBusyFlightsResponseConverter crazyAirConverter;
	@Mock
	private ToughJetResponseToBusyFlightsResponseConverter toughJetConverter;

	@Test
	public void busyFlightsServiceTest() {

		CrazyAirResponse crazyAirResponse = crazyAirResponse();
		CrazyAirRequest crazyAirRequest = crazyAirRequest();
		ToughJetResponse toughJetResponse = toughJetResponse();
		ToughJetRequest toughJetRequest = toughJetRequest();

		Mockito.when(crazyAirClient.getFlights(crazyAirRequest)).thenReturn(Optional.of(List.of(crazyAirResponse)));
		Mockito.when(toughJetClient.getFlights(toughJetRequest)).thenReturn(Optional.of(List.of(toughJetResponse)));
		Mockito.when(crazyAirConverter.convert(crazyAirResponse)).thenReturn(convertedCrazyAirResponse());
		Mockito.when(toughJetConverter.convert(toughJetResponse)).thenReturn(convertedToughJetResponse());

		List<BusyFlightsResponse> result = service.aggregateFlights(busyFlightsRequest());

		Assert.assertEquals(expectedBusyFlightsResponse(), result);

	}

	private BusyFlightsRequest busyFlightsRequest() {
		return BusyFlightsRequest.builder()
				.origin("LHR")
				.destination("AMS")
				.departureDate("2011-12-03")
				.returnDate("2011-12-04")
				.numberOfPassengers(3)
				.build();
	}
	private CrazyAirRequest crazyAirRequest() {
		return CrazyAirRequest.builder()
				.origin("LHR")
				.destination("AMS")
				.departureDate("2011-12-03")
				.returnDate("2011-12-04")
				.passengerCount(3)
				.build();
	}
	private ToughJetRequest toughJetRequest() {
		return ToughJetRequest.builder()
				.from("LHR")
				.to("AMS")
				.outboundDate("2011-12-03")
				.inboundDate("2011-12-04")
				.numberOfAdults(3)
				.build();
	}

	private List<BusyFlightsResponse> expectedBusyFlightsResponse() {
		return List.of(
				BusyFlightsResponse.builder()
						.airline("airline2")
						.supplier("ToughJet")
						.fare(107.43)
						.departureAirportCode("LHR")
						.destinationAirportCode("AMS")
						.departureDate("2011-12-03T08:15:30")
						.arrivalDate("2011-12-04T09:15:30")
						.build(),
				BusyFlightsResponse.builder()
						.airline("airline1")
						.supplier("CrazyAir")
						.fare(123.45)
						.departureAirportCode("LHR")
						.destinationAirportCode("AMS")
						.departureDate("2011-12-03T10:15:30")
						.arrivalDate("2011-12-04T11:15:30")
						.build()

		);
	}

	private BusyFlightsResponse convertedCrazyAirResponse() {
		return BusyFlightsResponse.builder()
				.airline("airline1")
				.supplier("CrazyAir")
				.fare(123.45)
				.departureAirportCode("LHR")
				.destinationAirportCode("AMS")
				.departureDate("2011-12-03T10:15:30")
				.arrivalDate("2011-12-04T11:15:30")
				.build();
	}

	private BusyFlightsResponse convertedToughJetResponse() {
		return BusyFlightsResponse.builder()
				.airline("airline2")
				.supplier("ToughJet")
				.fare(107.43)
				.departureAirportCode("LHR")
				.destinationAirportCode("AMS")
				.departureDate("2011-12-03T08:15:30")
				.arrivalDate("2011-12-04T09:15:30")
				.build();
	}

	private CrazyAirResponse crazyAirResponse() {
		return
				CrazyAirResponse.builder()
						.airline("airline1")
						.cabinclass("E")
						.price(123.45)
						.departureAirportCode("LHR")
						.destinationAirportCode("AMS")
						.departureDate("2011-12-03T10:15:30")
						.arrivalDate("2011-12-04T11:15:30")
						.build();
	}

	private ToughJetResponse toughJetResponse() {
		return ToughJetResponse.builder()
						.carrier("airline2")
						.basePrice(130.22)
						.tax(10)
						.discount(25)
						.departureAirportName("LHR")
						.arrivalAirportName("AMS")
						.outboundDateTime("2011-12-03T08:15:30Z")
						.inboundDateTime("2011-12-04T09:15:30Z")
						.build();
	}

}
