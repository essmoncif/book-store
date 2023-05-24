package com.store.catalog.app.domain.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

class AuditorAwareImplementation implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Anonymous");
    }
}
