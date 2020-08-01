package io.github.edmaputra.ed.edbase.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class EdDateTimeProvider implements DateTimeProvider {

  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(ZonedDateTime.now(ZoneId.systemDefault()));
  }
}
