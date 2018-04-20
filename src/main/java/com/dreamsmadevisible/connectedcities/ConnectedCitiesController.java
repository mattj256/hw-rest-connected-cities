package com.dreamsmadevisible.connectedcities;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedCitiesController {
  // TODO: move this into an external configuration file
  private static final String RESPONSE_YES = "yes";
  private static final String RESPONSE_NO = "no";

  // private static final String template = "Hello, %s!";
  // private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/connected")
  public ConnectedCitiesResponse greeting(
      @RequestParam(value="origin", defaultValue="") String origin,
      @RequestParam(value="destination", defaultValue="") String destination) {
    return new ConnectedCitiesResponse("yes");
  }

  /*
  @RequestMapping("/greeting2")
  public ConnectedCitiesResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new ConnectedCitiesResponse(counter.incrementAndGet(), String.format(template, name));
  }
  */
}
