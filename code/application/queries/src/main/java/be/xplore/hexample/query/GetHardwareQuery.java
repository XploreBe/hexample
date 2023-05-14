package be.xplore.hexample.query;

import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareId;
import jakarta.inject.Named;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@Named
public class GetHardwareQuery implements GetHardware {
  private static final Logger log = getLogger(GetHardwareQuery.class);

  private final HardwareArchive archive;

  public GetHardwareQuery(HardwareArchive archive) {
    this.archive = archive;
  }

  @Override
  public void execute(HardwareId hardwareId, Presenter presenter) {
    log.debug("Getting hardware with hardwareId {}", hardwareId);
    archive.get(hardwareId)
        .ifPresentOrElse(presenter::success, presenter::notFound);
  }
}
