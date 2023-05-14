package be.xplore.hexample.query;


import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;

import java.util.Optional;

public interface HardwareArchive {
  Optional<HardwareDto> get(HardwareId hardwareId);
}
