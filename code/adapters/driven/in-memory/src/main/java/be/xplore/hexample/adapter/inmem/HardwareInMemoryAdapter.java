package be.xplore.hexample.adapter.inmem;

import be.xplore.hexample.query.HardwareArchive;
import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareEntity;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Named
@Singleton
public class HardwareInMemoryAdapter implements HardwareArchive {
  private final Map<UUID, HardwareDto> hardwareMap;

  public HardwareInMemoryAdapter() {
    hardwareMap = new HashMap<>();
  }

  public HardwareInMemoryAdapter(Map<UUID, HardwareDto> hardwareMap) {
    this();
    this.hardwareMap.putAll(hardwareMap);
  }

  @Override
  public Optional<HardwareEntity> get(UUID hardwareId) {
    return Optional.ofNullable(hardwareMap.get(hardwareId))
        .map(it -> new HardwareEntity(hardwareId, it));
  }
}
