package com.covid.app.CovidApp;

import com.covid.app.CovidApp.service.CovidDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class CovidAppApplicationTests {

	@Autowired
	private CovidDataService covidDataService;

	@Test
	void contextLoads() {
		assertNotNull(covidDataService);
	}


	@Test
	void fetchDataTest() {
		String data = covidDataService.fetchData();
		assertEquals("Data", data);
	}

	@Test
	void fetchDataTest2() {
		String state = "Odisha";
		String city = "Jharsuguda";
		String data = covidDataService.getCovidData(state,city);
		assertEquals("27", data);
	}

//	@Test
//	void calculateTotalCasesTest() {
//		int totalCases = covidDataService.calculateTotalCases();
//		assertEquals(100, totalCases);
//	}

}
