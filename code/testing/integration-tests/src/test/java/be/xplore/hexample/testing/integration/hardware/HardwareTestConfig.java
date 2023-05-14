package be.xplore.hexample.testing.integration.hardware;

import be.xplore.hexample.adapter.inmem.InMemoryHardwareAdapter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;

@TestConfiguration
public class HardwareTestConfig {
    public static final String HARDWARE_PATH = "/hardware";

    @Bean
    @Primary
    public InMemoryHardwareAdapter inMemoryAdapter() {
        return new InMemoryHardwareAdapter(List.of(asusTufGamingX570()));
    }
}
