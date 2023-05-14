package be.xplore.hexample.api.hardware;

import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;

public interface GetHardware {
  void execute(HardwareId hardwareId, Presenter presenter);

  interface Presenter {

    void success(HardwareDto hardware);

    void notFound();
  }
}
