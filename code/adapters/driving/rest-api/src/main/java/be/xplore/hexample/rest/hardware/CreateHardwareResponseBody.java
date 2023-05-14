package be.xplore.hexample.rest.hardware;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public sealed interface CreateHardwareResponseBody {
  record Created(UUID hardwareId) implements CreateHardwareResponseBody {
    public Created {
      requireNonNull(hardwareId);
    }
  }

  record BadRequest(String validationError) implements CreateHardwareResponseBody {
    public BadRequest {
      requireNonNull(validationError);
    }
  }
}
