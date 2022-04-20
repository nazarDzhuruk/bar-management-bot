package com.drypalm.easybusiness.keyboard;

public enum KeyboardType {
    MENU("main menu"),
    SELL("sell"),
    ADD("add"),
    MAIN_ACCEPT("main_accept"),
    HELPFUL("helpful"),
    ALCOHOL_CATEGORY("alcohol_category"),
    SOFT_CATEGORY("soft_category");

    private final String keyboard;

    KeyboardType(String action) {
        this.keyboard = action;
    }

    public String getKeyboard() {
        return keyboard;
    }
}
