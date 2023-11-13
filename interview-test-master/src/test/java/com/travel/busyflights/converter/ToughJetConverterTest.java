package com.travel.busyflights.converter;

import com.travel.busyflights.client.CrazyAirClient;
import com.travel.busyflights.client.ToughJetClient;
import com.travel.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travel.busyflights.domain.crazyair.CrazyAirRequest;
import com.travel.busyflights.domain.crazyair.CrazyAirResponse;
import com.travel.busyflights.domain.toughjet.ToughJetRequest;
import com.travel.busyflights.domain.toughjet.ToughJetResponse;
import com.travel.busyflights.service.BusyFlightsService;
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
public class ToughJetConverterTest {

	@InjectMocks private ToughJetResponseToBusyFlightsResponseConverter converter;

	@Test
	public void busyFlightsServiceTest() {


		BusyFlightsResponse result = converter.convert(toughJetResponse());

		Assert.assertEquals(expectedBusyFlightsResponse(),result);

	}

	private BusyFlightsResponse expectedBusyFlightsResponse() {
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
