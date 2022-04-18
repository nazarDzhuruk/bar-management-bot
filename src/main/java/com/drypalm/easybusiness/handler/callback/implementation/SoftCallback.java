package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import org.springframework.stereotype.Component;

@Component
public class SoftCallback {
    private final KeyboardFactory keyboard;

    public SoftCallback(KeyboardFactory keyboard) {
        this.keyboard = keyboard;
    }
}
