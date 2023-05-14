package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.vocabulary.HardwareDto;

import java.util.UUID;

public record HardwareJsonResponse(UUID hardwareId, String vendor, String model) {
  public static HardwareJsonResponse ofDto(HardwareDto dto) {
    return new HardwareJsonResponse(dto.hardwareId().uuid(), dto.vendor(), dto.model());
  }
}
