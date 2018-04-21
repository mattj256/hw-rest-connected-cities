package com.dreamsmadevisible.connectedcities;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConnectedCitiesControllerTests {
  private static final String RESPONSE_YES = "yes";
  private static final String RESPONSE_NO = "no";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void missingOrUnknownParamsShouldReturnFalse() throws Exception {
    // missing origin and/or destination
    doTest("/connected", false);
    doTest("/connected?origin=", false);
    doTest("/connected?origin=Boston", false);
    doTest("/connected?destination=", false);
    doTest("/connected?destination=Newark", false);
    doTest("/connected?origin=Boston&destination=", false);
    doTest("/connected?origin=&destination=Newark", false);

    // unknown parameter
    doTest("/connected?origin=Boston&foo=Montreal", false);

    // unknown city
    doTest("/connected?origin=Boston&destination=Montreal", false);
  }

  @Test
  // TODO
  @Ignore
  public void connectedCitiesShouldReturnTrue() throws Exception {
    // directly connected
    doTest("Newark", "Boston", true);
    // roads go in both directions
    doTest("Boston", "Newark", true);

    // indirectly connected
    doTest("Boston", "Philadelphia", true);

    // TODO test URL encoding
  }

  @Test
  public void disconnectedCitiesShouldReturnFalse() throws Exception {
    doTest("Philadelphia", "Albany", false);
  }

  @Test
  public void cityConnectedToItself() throws Exception {
    // TODO
    // Known city is connected to itself
    doTest("Newark", "Newark", true);

    // Unknown city is not connected to itself
    doTest("Montreal", "Montreal", false);
  }

  private void doTest(String origin, String destination, boolean expectedResponse) throws Exception {
    doTest("/connected?origin=" + origin + "&destination=" + destination, expectedResponse);
  }

  private void doTest(String query, boolean expectedResponse) throws Exception {
    doTest(query, expectedResponse ? RESPONSE_YES : RESPONSE_NO);
  }

  private void doTest(String query, String expectedResponse) throws Exception {
    mockMvc.perform(get(query)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.response").value(expectedResponse));
  }
}
