package com.dreamsmadevisible.connectedcities;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  private void doTest(String query, boolean expectedResponse) throws Exception {
    doTest(query, expectedResponse ? RESPONSE_YES : RESPONSE_NO);
  }

  private void doTest(String query, String expectedResponse) throws Exception {
    mockMvc.perform(get(query)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.response").value(expectedResponse));
  }
}
