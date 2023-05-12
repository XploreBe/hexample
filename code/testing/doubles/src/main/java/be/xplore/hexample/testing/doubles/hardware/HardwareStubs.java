package be.xplore.hexample.testing.doubles.hardware;

import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareEntity;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public final class HardwareStubs {
  public static final UUID ASUS_TUF_ID = UUID.fromString("95cc0578-6661-478e-b7ae-81eae1e0b0d3");
  public static final UUID MSI_MEG_ID = randomUUID();

  private HardwareStubs() {
  }

  public static HardwareDto asusTufGamingX570() {
    return new HardwareDto("ASUS", "TUF Gaming X570");
  }

  public static HardwareEntity asusTufGamingX570Entity() {
    return new HardwareEntity(ASUS_TUF_ID, asusTufGamingX570());
  }

  public static HardwareDto msiMegZ970Ace() {
    return new HardwareDto("MSI", "MEG Z970 Ace");
  }

  public static HardwareEntity msiMegZ970AceEntity() {
    return new HardwareEntity(MSI_MEG_ID, msiMegZ970Ace());
  }
}
