package be.xplore.hexample.hardware;

import be.xplore.hexample.adapter.inmem.InMemoryHardwareAdapter;
import be.xplore.hexample.api.hardware.AddHardware;
import be.xplore.hexample.vocabulary.HardwareId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static be.xplore.hexample.hardware.TestDoubles.aHardwarePortFake;
import static be.xplore.hexample.hardware.TestDoubles.anAddAsusTufCommand;
import static be.xplore.hexample.hardware.TestDoubles.anAddMsiMegCommand;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;
import static org.assertj.core.api.Assertions.assertThat;

class AddHardwareUseCaseTest {
  private AddHardwareUseCase addHardwareUseCase;
  private InMemoryHardwareAdapter hardwarePort;

  @BeforeEach
  void setup() {
    hardwarePort = aHardwarePortFake();
    addHardwareUseCase = new AddHardwareUseCase(hardwarePort);
  }

  @Test
  void addNonExistingHardware_savesNewHardwareAndCallsPresenterSuccess() {
    var presenter = new TestPresenter();
    addHardwareUseCase.execute(anAddMsiMegCommand(), presenter);
    assertThat(presenter.hardwareId).isNotNull();
    assertThat(hardwarePort.getContextMap())
        .containsEntry(presenter.hardwareId, msiMegZ970Ace(presenter.hardwareId));
  }

  @Test
  void addExistingHardware_callsPresenterSuccessWithExistingId() {
    var presenter = new TestPresenter();
    addHardwareUseCase.execute(anAddAsusTufCommand(), presenter);
    assertThat(presenter.hardwareId).isEqualTo(ASUS_TUF_ID);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t"})
  void blankVendor_callsPresenterValidationErrorAndDoesNotSave(String vendor) {
    var initialContextSize = hardwarePort.getContextMap().size();
    var presenter = new TestPresenter();

    addHardwareUseCase.execute(new AddHardware.Command(vendor, "a model"), presenter);

    assertThat(presenter.validationError).isTrue();
    assertThat(hardwarePort.getContextMap()).hasSize(initialContextSize);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t"})
  void blankModel_callsPresenterValidationErrorAndDoesNotSave(String model) {
    var initialContextSize = hardwarePort.getContextMap().size();
    var presenter = new TestPresenter();

    addHardwareUseCase.execute(new AddHardware.Command("a vendor", model), presenter);

    assertThat(presenter.validationError).isTrue();
    assertThat(hardwarePort.getContextMap()).hasSize(initialContextSize);
  }

  private static final class TestPresenter implements AddHardware.Presenter {
    private HardwareId hardwareId;
    private boolean validationError;

    @Override
    public void success(HardwareId hardwareId) {
      this.hardwareId = hardwareId;
    }

    public void validationError(String message) {
      validationError = true;
    }
  }

}