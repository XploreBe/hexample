package be.xplore.hexample.adapter.inmem;

import be.xplore.hexample.hardware.Hardware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.MSI_MEG_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryHardwareAdapterTest {
  private InMemoryHardwareAdapter adapter;

  @BeforeEach
  void setup() {
    adapter = new InMemoryHardwareAdapter(List.of(asusTufGamingX570()));
  }

  @Test
  void get_FindsHardwareByVendorAndModel() {
    var toBeFound = asusTufGamingX570();
    assertThat(adapter.findByVendorAndModel(toBeFound.vendor(), toBeFound.model()))
        .hasValue(Hardware.ofDto(asusTufGamingX570()));
  }

  @Test
  void save_savesHardwareToContext() {
    adapter.save(Hardware.ofDto(msiMegZ970Ace()));
    assertThat(adapter.getContextMap()).containsEntry(MSI_MEG_ID, msiMegZ970Ace());
  }

  @Test
  void get_FindsEntity() {
    assertThat(adapter.get(ASUS_TUF_ID)).hasValue(asusTufGamingX570());
  }
}