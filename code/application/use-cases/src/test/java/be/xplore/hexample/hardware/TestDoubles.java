package be.xplore.hexample.hardware;

import be.xplore.hexample.adapter.inmem.InMemoryHardwareAdapter;
import be.xplore.hexample.api.hardware.AddHardware;

import java.util.List;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;

public final class TestDoubles {
  private TestDoubles() {
  }

  public static AddHardware.Command anAddMsiMegCommand() {
    var msiMeg = msiMegZ970Ace();
    return new AddHardware.Command(msiMeg.model(), msiMeg.vendor());
  }

  public static AddHardware.Command anAddAsusTufCommand() {
    var msiMeg = asusTufGamingX570();
    return new AddHardware.Command(msiMeg.model(), msiMeg.vendor());
  }

  public static InMemoryHardwareAdapter aHardwarePortFake() {
    return new InMemoryHardwareAdapter(List.of(asusTufGamingX570()));
  }
}
