package be.xplore.hexample.testing.doubles.hardware;

import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;

import java.util.UUID;

public final class HardwareStubs {
  public static final HardwareId ASUS_TUF_ID = new HardwareId(UUID.fromString("95cc0578-6661-478e-b7ae-81eae1e0b0d3"));
  public static final HardwareId MSI_MEG_ID = new HardwareId(UUID.fromString("09ad78e6-af9e-455d-8910-ae6c888b34b3"));

  private HardwareStubs() {
  }

  public static HardwareDto asusTufGamingX570() {
    return new HardwareDto(ASUS_TUF_ID, "ASUS", "TUF Gaming X570");
  }

  public static HardwareDto msiMegZ970Ace() {
    return new HardwareDto(MSI_MEG_ID, "MSI", "MEG Z970 Ace");
  }

  public static HardwareDto msiMegZ970Ace(HardwareId id) {
    return new HardwareDto(id, "MSI", "MEG Z970 Ace");
  }
}
