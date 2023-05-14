package be.xplore.hexample.hardware;

import java.util.Optional;

public interface HardwarePort {
    void save(Hardware hardware);

    Optional<Hardware> findByVendorAndModel(String vendor, String model);
}
