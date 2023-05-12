package be.xplore.hexample.api.hardware;

import be.xplore.hexample.vocabulary.HardwareEntity;

import java.util.UUID;

public interface GetHardware {
  void execute(UUID hardwareId, Presenter presenter);

  interface Presenter {

    void success(HardwareEntity hardware);

    void notFound();
  }
}
