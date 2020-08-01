package io.github.edmaputra.ed.edbase.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "edAuditorAware", dateTimeProviderRef = "edDateTimeProvider")
public class EdJpaAuditConfiguration {

  @Bean
  public AuditorAware<String> edAuditorAware() {
    return new EdAuditorAware();
  }

  @Bean
  public DateTimeProvider edDateTimeProvider() {
    return new EdDateTimeProvider();
  }
}
