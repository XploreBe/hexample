package be.xplore.hexample.query;

import be.xplore.hexample.adapter.inmem.InMemoryHardwareAdapter;
import be.xplore.hexample.api.hardware.GetHardware;
import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.MSI_MEG_ID;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.asusTufGamingX570;
import static be.xplore.hexample.testing.doubles.hardware.HardwareStubs.msiMegZ970Ace;
import static org.assertj.core.api.Assertions.assertThat;


class GetHardwareQueryTest {
  private GetHardwareQuery query;
  private TestPresenter presenter;

  @BeforeEach
  void setup() {
    query = new GetHardwareQuery(new InMemoryHardwareAdapter(List.of(
        asusTufGamingX570(),
        msiMegZ970Ace())));
    presenter = new TestPresenter();
  }

  @Test
  void whenQueryFindsHardware_callPresenterSuccess() {
    query.execute(MSI_MEG_ID, presenter);
    assertThat(presenter.hardware).isEqualTo(msiMegZ970Ace());
  }

  @Test
  void whenQueryDoesNotFindHardware_callNotFound() {
    query.execute(new HardwareId(), presenter);
    assertThat(presenter.notFound).isTrue();
    assertThat(presenter.hardware).isNull();
  }

  private static final class TestPresenter implements GetHardware.Presenter {
    HardwareDto hardware;
    boolean notFound;

    @Override
    public void success(HardwareDto hardware) {
      this.hardware = hardware;
    }

    @Override
    public void notFound() {
      notFound = true;
    }
  }
}