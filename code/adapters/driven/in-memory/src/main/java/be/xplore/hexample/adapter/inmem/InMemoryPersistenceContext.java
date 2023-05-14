package be.xplore.hexample.adapter.inmem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Collections.unmodifiableMap;
import static java.util.Optional.ofNullable;

class InMemoryPersistenceContext<K, E> {
  private final Function<E, K> keyMapper;
  private final Map<K, E> contextMap;

  InMemoryPersistenceContext(Function<E, K> keyMapper) {
    this.keyMapper = keyMapper;
    this.contextMap = new HashMap<>();
  }

  void save(E entity) {
    contextMap.put(keyMapper.apply(entity), entity);
  }

  Optional<E> get(K id) {
    return ofNullable(contextMap.get(id));
  }

  void setContext(Collection<E> entities) {
    contextMap.clear();
    entities.forEach(entity -> contextMap.put(keyMapper.apply(entity), entity));
  }

  Map<K, E> getContextMap() {
    return unmodifiableMap(contextMap);
  }
}
