package com.drypalm.easybusiness.keyboard;

import com.drypalm.easybusiness.keyboard.implementation.KeyboardService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Factory implements KeyboardFactory {
    private final List<KeyboardService> keyboardService;

    private Map<KeyboardType, KeyboardService> keyboardMap;

    public Factory(List<KeyboardService> keyboardService) {
        this.keyboardService = keyboardService;
    }

    @PostConstruct
    private void init() {
        keyboardMap = new EnumMap<>(KeyboardType.class);
        keyboardService.forEach(k -> keyboardMap.put(k.getImplementation(), k));
    }

    @Override
    public KeyboardService getService(KeyboardType type) {
        return Optional.ofNullable(keyboardMap.get(type)).orElseThrow();
    }
}
