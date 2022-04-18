package com.drypalm.easybusiness.keyboard;

import com.drypalm.easybusiness.keyboard.implementation.KeyboardService;

public interface KeyboardFactory {
    KeyboardService getService(KeyboardType type);
}
