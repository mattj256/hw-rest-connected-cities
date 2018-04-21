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

  // TODO: move this into an external configuration file
  private static final String RESPONSE_YES = "yes";
  private static final String RESPONSE_NO = "no";
  @Autowired CityRepository cityRepository;

  @RequestMapping("/connected")
  public ConnectedCitiesResponse greeting(
      @RequestParam(value="origin", defaultValue="") String origin,
      @RequestParam(value="destination", defaultValue="") String destination) {
    if (isNullOrEmpty(origin) || isNullOrEmpty(destination)) return getResponse(false);
    // TODO normalize: remove leading and trailing spaces, squash internal spaces, make all caps
    City cityA = cityRepository.findByName(origin);
    City cityB = cityRepository.findByName(destination);
    if (cityA == null || cityB == null) return getResponse(false);

    boolean connected = cityA.isConnected(cityB);
    return getResponse(connected);

    // if (cityA.getName().equals(cityB.getName())) return getResponse(true);

    // // TODO handle indirect connections
    // if (cityA.hasNeighbor(cityB) || cityB.hasNeighbor(cityA)) return getResponse(true);

    // // return getResponse(areConnected(cityA, cityB));

    // return getResponse(false);
  }

  private static ConnectedCitiesResponse getResponse(boolean response) {
    return new ConnectedCitiesResponse(response ? RESPONSE_YES : RESPONSE_NO);
  }

  private static boolean isNullOrEmpty(String s) {
    return s == null || s.isEmpty();
  }
}
