//package com.travel.busyflights;
//
//import com.travel.busyflights.client.CrazyAirClient;
//import com.travel.busyflights.client.ToughJetClient;
//import com.travel.busyflights.converter.CrazyAirResponseToBusyFlightsResponseConverter;
//import com.travel.busyflights.converter.ToughJetResponseToBusyFlightsResponseConverter;
//import com.travel.busyflights.domain.busyflights.BusyFlightsRequest;
//import com.travel.busyflights.domain.busyflights.BusyFlightsResponse;
//import com.travel.busyflights.domain.crazyair.CrazyAirRequest;
//import com.travel.busyflights.domain.crazyair.CrazyAirResponse;
//import com.travel.busyflights.domain.toughjet.ToughJetRequest;
//import com.travel.busyflights.domain.toughjet.ToughJetResponse;
//import com.travel.busyflights.service.BusyFlightsService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.text.DecimalFormat;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class BusyFlightsApplicationTests {
//
////	@Test
////	public void test() {
//////		String format = LocalDateTime.parse("2012-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ISO_DATE_TIME);
//////		String format = "2014-12-03T10:15:30";
////		String format = LocalDateTime.ofInstant(Instant.parse("2012-12-03T10:15:30Z"), ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME);
////		String format2 = LocalDateTime.parse("2023-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME).format(DateTimeFormatter.ISO_DATE_TIME);
////
////		ArrayList<BusyFlightsResponse> list = new ArrayList<>();
////		list.add(BusyFlightsResponse.builder().fare(10.89).build());
////		list.add(BusyFlightsResponse.builder().fare(101.89).build());
////		list.add(BusyFlightsResponse.builder().fare(30.89).build());
////		list.add(BusyFlightsResponse.builder().fare(10.90).build());
////		list.stream().forEach(System.out::println);
////		List<BusyFlightsResponse> collect = list.stream().sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());
////		collect.stream().forEach(System.out::println);
////		System.out.println("instant: "+format);
////		System.out.println("localdatetime: "+format2);
////
////		DecimalFormat df = new DecimalFormat("#.##");
////		double d = 102.32 * 1.10 * (1 - (25 / 100.00));
////		System.out.println(df.format(102.32 * (1 - (25 / 100.00)) * (1 + (10 / 100.00))));
////		System.out.println(df.format(d));
////	}
////
//	@Test
//	public void contextLoads() {
//	}
//
//}
