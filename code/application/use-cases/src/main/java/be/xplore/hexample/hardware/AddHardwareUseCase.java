package be.xplore.hexample.hardware;

import be.xplore.hexample.api.hardware.AddHardware;
import be.xplore.hexample.vocabulary.HardwareId;
import jakarta.inject.Named;

@Named
public class AddHardwareUseCase implements AddHardware {
  private final HardwarePort hardwarePort;

  public AddHardwareUseCase(HardwarePort hardwarePort) {
    this.hardwarePort = hardwarePort;
  }

  @Override
  public void execute(Command command, Presenter presenter) {
    try {
      presenter.success(createIfNotExists(command));
    } catch (IllegalArgumentException e) {
      presenter.validationError(e.getMessage());
    }
  }

  private HardwareId createIfNotExists(Command command) {
    return hardwarePort.findByVendorAndModel(command.vendor(), command.model())
        .map(Hardware::getHardwareId)
        .orElseGet(() -> createAndSaveNewHardware(command));
  }

  private HardwareId createAndSaveNewHardware(Command command) {
    var createdHardware = Hardware.createNew(command.vendor(), command.model());
    hardwarePort.save(createdHardware);
    return createdHardware.getHardwareId();
  }
}
