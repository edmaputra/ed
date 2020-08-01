package io.github.edmaputra.ed.edbase.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class EdAuditorAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String auditor = Objects.nonNull(authentication) ? authentication.getName() : "anonymous";
    return Optional.of(auditor);
  }
}
