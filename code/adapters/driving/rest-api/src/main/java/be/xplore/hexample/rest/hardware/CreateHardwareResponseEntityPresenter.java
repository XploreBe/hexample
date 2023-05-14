package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.api.hardware.AddHardware;
import be.xplore.hexample.vocabulary.HardwareId;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;

public class CreateHardwareResponseEntityPresenter implements AddHardware.Presenter {
  private ResponseEntity<CreateHardwareResponseBody> response;

  @Override
  public void success(HardwareId hardwareId) {
    response = ResponseEntity
        .status(CREATED)
        .body(new CreateHardwareResponseBody.Created(hardwareId.uuid()));
  }

  @Override
  public void validationError(String message) {
    response = ResponseEntity.badRequest()
        .body(new CreateHardwareResponseBody.BadRequest(message));
    }

  public ResponseEntity<CreateHardwareResponseBody> getResponse() {
    return response;
  }
}
