package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/hardware")
public class HardwareController {
  private final GetHardware getHardware;

  public HardwareController(GetHardware getHardware) {
    this.getHardware = getHardware;
  }

  @GetMapping("/{hardwareId}")
  public ResponseEntity<HardwareJsonResponse> getHardwareById(@PathVariable UUID hardwareId) {
    var presenter = new GetHardwarePresenter();
    getHardware.execute(hardwareId, presenter);
    return presenter.getResponse();
  }

  private static final class GetHardwarePresenter implements GetHardware.Presenter {
    private ResponseEntity<HardwareJsonResponse> response;

    private GetHardwarePresenter() {
    }

    @Override
    public void success(HardwareEntity hardware) {
      response = ResponseEntity.ok(HardwareJsonResponse.ofEntity(hardware));
    }

    @Override
    public void notFound() {
      response = ResponseEntity.notFound().build();
    }

    public ResponseEntity<HardwareJsonResponse> getResponse() {
      return response;
    }
  }
}
