package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareDto;
import org.springframework.http.ResponseEntity;

final class GetHardwareResponseBuilder implements GetHardware.Presenter {
  private ResponseEntity<HardwareJsonResponse> response;

  GetHardwareResponseBuilder() {
  }

  @Override
  public void success(HardwareDto hardware) {
    response = ResponseEntity.ok(HardwareJsonResponse.ofDto(hardware));
  }

  @Override
  public void notFound() {
    response = ResponseEntity.notFound().build();
  }

  public ResponseEntity<HardwareJsonResponse> getResponse() {
    return response;
  }
}
