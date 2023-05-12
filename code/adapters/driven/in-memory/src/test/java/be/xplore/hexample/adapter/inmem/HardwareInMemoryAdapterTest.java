package be.xplore.hexample.adapter.inmem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.MSI_MEG_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570Entity;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;
import static org.assertj.core.api.Assertions.assertThat;

class HardwareInMemoryAdapterTest {
  private HardwareInMemoryAdapter adapter;

  @BeforeEach
  void setup() {
    adapter = new HardwareInMemoryAdapter(Map.of(
        ASUS_TUF_ID, asusTufGamingX570(),
        MSI_MEG_ID, msiMegZ970Ace()));
  }

  @Test
  void getHardwareFindsEntity() {
    assertThat(adapter.get(ASUS_TUF_ID)).hasValue(asusTufGamingX570Entity());
  }
}