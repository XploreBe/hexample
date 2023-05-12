package be.xplore.hexample.query;

import be.xplore.hexample.vocabulary.HardwareEntity;

import java.util.Optional;
import java.util.UUID;

public interface HardwareArchive {
  Optional<HardwareEntity> get(UUID hardwareId);
}
