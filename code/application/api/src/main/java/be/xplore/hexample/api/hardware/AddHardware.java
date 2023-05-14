package be.xplore.hexample.api.hardware;

import be.xplore.hexample.vocabulary.HardwareId;

public interface AddHardware {
  void execute(Command command, Presenter presenter);

  interface Presenter {
    void success(HardwareId hardwareId);

    void validationError(String message);
  }

  record Command(String model, String vendor) {
  }
}
