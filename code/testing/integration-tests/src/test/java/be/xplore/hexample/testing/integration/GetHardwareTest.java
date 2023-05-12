package be.xplore.hexample.testing.integration;

import be.xplore.hexample.SpringBootStarter;
import be.xplore.hexample.adapter.inmem.HardwareInMemoryAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {SpringBootStarter.class, GetHardwareTest.TestConfig.class})
public class GetHardwareTest {
  private static final String GET_HARDWARE_PATH = "/hardware/{hardwareId}";
  @Autowired
  private MockMvc mockMvc;

  @Value("classpath:hardware/get-asus-tuf-response.json")
  private Resource jsonFile;

  @Test
  void getHardwareReturnsCorrectResponseEntity() throws Exception {
    mockMvc.perform(get(GET_HARDWARE_PATH, ASUS_TUF_ID))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(content().json(jsonFile.getContentAsString(StandardCharsets.UTF_8)));
  }

  @Test
  void getHardwareReturnsNotFound() throws Exception {
    mockMvc.perform(get(GET_HARDWARE_PATH, randomUUID()))
        .andExpect(status().isNotFound());
  }

  @TestConfiguration
  public static class TestConfig {
    @Bean
    @Primary
    public HardwareInMemoryAdapter inMemoryAdapter() {
      return new HardwareInMemoryAdapter(Map.of(
          ASUS_TUF_ID, asusTufGamingX570()
      ));
    }
  }
}
