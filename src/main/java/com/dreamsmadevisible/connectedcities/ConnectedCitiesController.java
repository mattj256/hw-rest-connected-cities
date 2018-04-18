package com.dreamsmadevisible.connectedcities;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectedCitiesController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting2")
  public ConnectedCitiesResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new ConnectedCitiesResponse(counter.incrementAndGet(), String.format(template, name));
  }
}
