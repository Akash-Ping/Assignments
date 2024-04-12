package com.covid.app.CovidApp.service;


import com.covid.app.CovidApp.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CovidDataService {

    @Autowired
    RestTemplate restTemplate;
    public String getCovidData(String state, String cityName)
    {
        String Ustate = state.substring(0,1).toUpperCase()+state.substring(1).toLowerCase();
        String Ucity = cityName.substring(0,1).toUpperCase()+cityName.substring(1).toLowerCase();

        Map<String,Object> map = restTemplate.getForObject(Util.COVID_DATA_API, Map.class);

        Map<String, Object> stateObjectMap = (Map<String, Object>) map.get(Ustate);
        Map<String, Object> districtData = (Map<String, Object>) stateObjectMap.get("districtData");
        Map<String, Object> city = (Map<String, Object>) districtData.get(Ucity);
        Integer active = (Integer) city.get("active");
      

        System.out.println(active);

        return active+"";



    }

    public String fetchData() {
        // Fetch data related to Covid
        return "Data";
    }

}
