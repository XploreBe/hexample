package be.xplore.hexample.vocabulary;

import java.util.UUID;

public record HardwareEntity(UUID hardwareId,
                             HardwareDto hardware) {
}
