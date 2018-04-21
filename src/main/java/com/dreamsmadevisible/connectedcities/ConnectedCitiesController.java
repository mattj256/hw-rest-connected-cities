package com.dreamsmadevisible.connectedcities;

import com.dreamsmadevisible.connectedcities.model.City;
import com.dreamsmadevisible.connectedcities.model.CityRepository;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedCitiesController {
  private static final Logger log = LoggerFactory.getLogger(ConnectedCitiesController.class);

  private static final String RESPONSE_YES = "yes";
  private static final String RESPONSE_NO = "no";
  @Autowired CityRepository cityRepository;

  @RequestMapping("/connected")
  public ConnectedCitiesResponse greeting(
      @RequestParam(value="origin", defaultValue="") String origin,
      @RequestParam(value="destination", defaultValue="") String destination) {
    if (isNullOrEmpty(origin) || isNullOrEmpty(destination)) return getResponse(false);

    City cityA = cityRepository.findByName(origin);
    City cityB = cityRepository.findByName(destination);

    if (cityA == null || cityB == null) {
      // At least one city is not in the db
      return getResponse(false);
    }

    boolean connected = cityA.isConnected(cityB);
    return getResponse(connected);
  }

  private static ConnectedCitiesResponse getResponse(boolean response) {
    return new ConnectedCitiesResponse(response ? RESPONSE_YES : RESPONSE_NO);
  }

  private static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }
}
