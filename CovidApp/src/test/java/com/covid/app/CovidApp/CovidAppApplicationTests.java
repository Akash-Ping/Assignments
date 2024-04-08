package com.covid.app.CovidApp;

import com.covid.app.CovidApp.service.CovidDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CovidAppApplicationTests {

	@Autowired
	private CovidDataService covidDataService;

	@Test
	void contextLoads() {
	}

	@Test
	void fetchDataTest() {
		String data = covidDataService.fetchData();
		assertEquals("Data", data);
	}

}
