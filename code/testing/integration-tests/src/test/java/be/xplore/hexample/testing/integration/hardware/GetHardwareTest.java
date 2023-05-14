package be.xplore.hexample.testing.integration.hardware;

import be.xplore.hexample.SpringBootStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.integration.TestUtil.getContentAsString;
import static be.xplore.hexample.testing.integration.hardware.HardwareTestConfig.HARDWARE_PATH;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {SpringBootStarter.class, HardwareTestConfig.class})
class GetHardwareTest {
  private static final String GET_HARDWARE_PATH = HARDWARE_PATH + "/{hardwareId}";
  @Autowired
  private MockMvc mockMvc;

  @Test
  void getHardwareReturnsCorrectResponseEntity() throws Exception {
    mockMvc.perform(get(GET_HARDWARE_PATH, ASUS_TUF_ID.uuid()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(getContentAsString("hardware/get-asus-tuf-response.json")));
  }

  @Test
  void getHardwareReturnsNotFound() throws Exception {
    mockMvc.perform(get(GET_HARDWARE_PATH, randomUUID()))
        .andExpect(status().isNotFound());
  }
}
