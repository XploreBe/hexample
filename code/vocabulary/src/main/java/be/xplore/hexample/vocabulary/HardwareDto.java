package be.xplore.hexample.vocabulary;

import static java.util.Objects.requireNonNull;

public record HardwareDto(String vendor,
                          String model
) {
  public HardwareDto {
    requireNonNull(vendor);
    requireNonNull(model);
  }
}
