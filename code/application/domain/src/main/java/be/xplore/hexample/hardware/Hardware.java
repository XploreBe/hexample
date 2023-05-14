package be.xplore.hexample.hardware;

import be.xplore.hexample.vocabulary.HardwareDto;
import be.xplore.hexample.vocabulary.HardwareId;

import java.util.Objects;
import java.util.StringJoiner;

import static be.xplore.hexample.util.RequireUtil.requireNotBlank;
import static java.util.Objects.requireNonNull;

public class Hardware {
  private final HardwareId hardwareId;
  private final String vendor;
  private final String model;

  private Hardware(HardwareId hardwareId, String vendor, String model) {
    this.hardwareId = requireNonNull(hardwareId);
    this.vendor = requireNotBlank(vendor, "vendor");
    this.model = requireNotBlank(model, "model");
  }

  public static Hardware createNew(String vendor, String model) {
    return new Hardware(new HardwareId(), vendor, model);
  }

  public static Hardware ofDto(HardwareDto dto) {
    return new Hardware(dto.hardwareId(), dto.vendor(), dto.model());
  }

  public HardwareDto toDto() {
    return new HardwareDto(hardwareId, vendor, model);
  }

  public HardwareId getHardwareId() {
    return hardwareId;
  }

  public String getVendor() {
    return vendor;
  }

  public String getModel() {
    return model;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Hardware.class.getSimpleName() + "[", "]")
        .add("hardwareId=" + hardwareId)
        .add("vendor='" + vendor + "'")
        .add("model='" + model + "'")
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Hardware hardware = (Hardware) o;
    return Objects.equals(hardwareId, hardware.hardwareId)
        && Objects.equals(vendor, hardware.vendor)
        && Objects.equals(model, hardware.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hardwareId, vendor, model);
  }
}
