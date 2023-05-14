package be.xplore.hexample.testing.integration.hardware;

import be.xplore.hexample.SpringBootStarter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static be.xplore.hexample.testing.integration.TestUtil.UUID_PATTERN;
import static be.xplore.hexample.testing.integration.TestUtil.getContentAsString;
import static be.xplore.hexample.testing.integration.hardware.HardwareTestConfig.HARDWARE_PATH;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {SpringBootStarter.class, HardwareTestConfig.class})
class AddHardwareTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void success_respondsWithHardwareId() throws Exception {
        mockMvc.perform(post(HARDWARE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContentAsString("hardware/post-msi-meg-request.json")))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("hardwareId", matchesRegex(UUID_PATTERN)));
    }

    @Test
    void vendorBlank_respondsBadRequest() throws Exception {
        mockMvc.perform(post(HARDWARE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContentAsString("hardware/post-blank-vendor-request.json")))
            .andExpect(status().isBadRequest());
    }

    @Test
    void modelBlank_respondsBadRequest() throws Exception {
        mockMvc.perform(post(HARDWARE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getContentAsString("hardware/post-blank-model-request.json")))
            .andExpect(status().isBadRequest());
    }
}
