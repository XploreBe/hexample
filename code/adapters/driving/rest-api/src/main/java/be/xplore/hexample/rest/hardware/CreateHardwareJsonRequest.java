package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.api.hardware.AddHardware;

public record CreateHardwareJsonRequest(String vendor, String model) {
  public AddHardware.Command toCommand() {
    return new AddHardware.Command(model, vendor);
  }
}