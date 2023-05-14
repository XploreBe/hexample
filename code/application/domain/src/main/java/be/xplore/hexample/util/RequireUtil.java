package be.xplore.hexample.util;

import static java.util.Optional.ofNullable;
import static java.util.function.Predicate.not;

public final class RequireUtil {
  private RequireUtil() {
  }

  public static String requireNotBlank(String s, String displayName) {
    return ofNullable(s)
        .filter(not(String::isBlank))
        .orElseThrow(() -> new IllegalArgumentException("%s should not be blank.".formatted(displayName)));
  }
}
