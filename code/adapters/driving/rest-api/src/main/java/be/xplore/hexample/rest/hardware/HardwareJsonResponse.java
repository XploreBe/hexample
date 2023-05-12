package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.vocabulary.HardwareEntity;

import java.util.UUID;

public record HardwareJsonResponse(UUID hardwareId, String vendor, String model) {
  public static HardwareJsonResponse ofEntity(HardwareEntity entity) {
    var dto = entity.hardware();
    return new HardwareJsonResponse(entity.hardwareId(), dto.vendor(), dto.model());
  }
}
