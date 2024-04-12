package com.covid.app.CovidApp.controller;


import com.covid.app.CovidApp.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController //to denote as rest architecture
@RequestMapping("/covid-data")
public class CovidController {

    @Autowired
    CovidDataService covidDataService;

    //localhost:8080/covid-data/citywise/delhi
    @GetMapping("/{state}/{city}")
    public String getCovidCases(@PathVariable String state, @PathVariable String city) throws IOException
    {
        String covidData = covidDataService.getCovidData(state,city);
        return "Covid Active Cases in "+state +" , "+city+" is "+covidData;
    }

}
