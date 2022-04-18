package com.drypalm.easybusiness.keyboard;

public enum KeyboardType {
    MENU("main menu"),
    SELL("sell"),
    ADD("add"),
    HELPFUL("helpful"),
    ALCOHOL_CATEGORY("category");

    private final String button;

    KeyboardType(String action) {
        this.button = action;
    }

    public String getButton() {
        return button;
    }
}
