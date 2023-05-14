package be.xplore.hexample.rest.hardware;

import be.xplore.hexample.api.hardware.AddHardware;
import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/hardware")
public class HardwareController {
  private final GetHardware getHardware;
  private final AddHardware addHardware;

  public HardwareController(GetHardware getHardware, AddHardware addHardware) {
    this.getHardware = getHardware;
    this.addHardware = addHardware;
  }

  @GetMapping("/{hardwareId}")
  public ResponseEntity<HardwareJsonResponse> getHardwareById(@PathVariable UUID hardwareId) {
    var presenter = new GetHardwareResponseBuilder();
    getHardware.execute(new HardwareId(hardwareId), presenter);
    return presenter.getResponse();
  }

  @PostMapping
  public ResponseEntity<CreateHardwareResponseBody> postHardware(@RequestBody CreateHardwareJsonRequest requestBody) {
    var presenter = new CreateHardwareResponseEntityPresenter();
    addHardware.execute(requestBody.toCommand(), presenter);
    return presenter.getResponse();
  }

}
