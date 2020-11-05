package com.lambdaschool.javazoos.services;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class ZooAuditing implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String uname = "SYSTEM";
        return Optional.of(uname);
    }
}
