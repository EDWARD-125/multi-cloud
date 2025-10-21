package com.carcafe.vmprovisioningapi.factory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FactoryProvider {

    private final List<VMFactory> factories;
    private Map<String, VMFactory> map;

    @Autowired
    public FactoryProvider(List<VMFactory> factories) {
        this.factories = factories;
    }

    @PostConstruct
    public void init() {
        map = factories.stream().collect(Collectors.toMap(
            f -> f.providerName().toLowerCase(), f -> f
        ));
    }

    public VMFactory getFactory(String provider) {
        return map.get(provider.toLowerCase());
    }
}
