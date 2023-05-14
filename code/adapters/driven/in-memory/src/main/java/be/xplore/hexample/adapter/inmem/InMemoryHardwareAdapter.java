package be.xplore.hexample.adapter.inmem;

import be.xplore.hexample.hardware.Hardware;
import be.xplore.hexample.hardware.HardwarePort;
import be.xplore.hexample.query.HardwareArchive;
import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;
import jakarta.inject.Named;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Named
public class InMemoryHardwareAdapter implements HardwarePort, HardwareArchive {
  private final InMemoryPersistenceContext<HardwareId, HardwareDto> persistenceContext;

  public InMemoryHardwareAdapter(InMemoryPersistenceContext<HardwareId, HardwareDto> persistenceContext) {
    this.persistenceContext = persistenceContext;
  }

  public InMemoryHardwareAdapter() {
    this(new InMemoryPersistenceContext<>(HardwareDto::hardwareId));
  }

  public InMemoryHardwareAdapter(Collection<HardwareDto> initialHardwareContext) {
    this();
    this.persistenceContext.setContext(initialHardwareContext);
  }

  @Override
  public Optional<HardwareDto> get(HardwareId hardwareId) {
    return persistenceContext.get(hardwareId);
  }

  @Override
  public void save(Hardware hardware) {
    persistenceContext.save(hardware.toDto());
  }

  @Override
  public Optional<Hardware> findByVendorAndModel(String vendor, String model) {
    return persistenceContext.getContextMap()
        .entrySet().stream()
        .filter(entry -> entry.getValue().vendor().equals(vendor))
        .filter(entry -> entry.getValue().model().equals(model))
        .findFirst()
        .map(Map.Entry::getValue)
        .map(Hardware::ofDto);
  }

  public Map<HardwareId, HardwareDto> getContextMap() {
    return persistenceContext.getContextMap();
  }
}
