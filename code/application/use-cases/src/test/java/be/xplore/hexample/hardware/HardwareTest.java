package be.xplore.hexample.hardware;

import org.junit.jupiter.api.Test;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static org.assertj.core.api.Assertions.assertThat;

class HardwareTest {
  @Test
  void hardwareConstructsFromDto() {
    var hardware = Hardware.ofDto(asusTufGamingX570());
    assertThat(hardware).satisfies(hw -> {
      assertThat(hw.getHardwareId()).isNotNull();
      assertThat(hw.getVendor()).isEqualTo("ASUS");
      assertThat(hw.getModel()).isEqualTo("TUF Gaming X570");
    });
  }
}