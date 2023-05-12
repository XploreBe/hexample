package be.xplore.hexample.query;

import be.xplore.hexample.adapter.inmem.HardwareInMemoryAdapter;
import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.ASUS_TUF_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.MSI_MEG_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970AceEntity;
import static org.assertj.core.api.Assertions.assertThat;


class GetHardwareQueryTest {
  private GetHardwareQuery query;
  private TestPresenter presenter;

  @BeforeEach
  void setup() {
    query = new GetHardwareQuery(new HardwareInMemoryAdapter(Map.of(
        ASUS_TUF_ID, asusTufGamingX570(),
        MSI_MEG_ID, msiMegZ970Ace())));
    presenter = new TestPresenter();
  }

  @Test
  void whenQueryFindsHardware_callPresenterSuccess() {
    query.execute(MSI_MEG_ID, presenter);
    assertThat(presenter.hardware).isEqualTo(msiMegZ970AceEntity());
  }

  @Test
  void whenQueryDoesNotFindHardware_callNotFound() {
    query.execute(UUID.randomUUID(), presenter);
    assertThat(presenter.notFound).isTrue();
    assertThat(presenter.hardware).isNull();
  }

  private static final class TestPresenter implements GetHardware.Presenter {
    HardwareEntity hardware;
    boolean notFound;

    @Override
    public void success(HardwareEntity hardware) {
      this.hardware = hardware;
    }

    @Override
    public void notFound() {
      notFound = true;
    }
  }
}