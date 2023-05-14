package be.xplore.hexample.vocabulary;

import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

public record HardwareId(UUID uuid) {
  public HardwareId {
    requireNonNull(uuid);
  }

  public HardwareId() {
    this(randomUUID());
  }
}
