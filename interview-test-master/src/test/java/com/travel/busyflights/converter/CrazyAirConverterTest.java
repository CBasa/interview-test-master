package com.travel.busyflights.converter;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrazyAirConverterTest {

	@InjectMocks private CrazyAirResponseToBusyFlightsResponseConverter converter;

	@Test
	public void busyFlightsServiceTest() {


		BusyFlightsResponse result = converter.convert(crazyAirResponse());

		Assert.assertEquals(expectedBusyFlightsResponse(),result);

	}

	private BusyFlightsResponse expectedBusyFlightsResponse() {
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

	private CrazyAirResponse crazyAirResponse() {
		return CrazyAirResponse.builder()
						.airline("airline1")
						.cabinclass("E")
						.price(123.45)
						.departureAirportCode("LHR")
						.destinationAirportCode("AMS")
						.departureDate("2011-12-03T10:15:30")
						.arrivalDate("2011-12-04T11:15:30")
						.build();
	}

}
