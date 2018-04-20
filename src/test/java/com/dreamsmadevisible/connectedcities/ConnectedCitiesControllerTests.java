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
  public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
    this.mockMvc.perform(get("/connected")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.response").value(RESPONSE_YES));
  }

  /*
  @Test
  public void paramGreetingShouldReturnTailoredMessage() throws Exception {
    this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
  }
  */
}
