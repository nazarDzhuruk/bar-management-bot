package com.drypalm.easybusiness.keyboard;

public enum MainButtons {
    MAIN_MENU("MAIN MENU"),
    BACK("<<");
    private final String button;

    MainButtons(String button) {
        this.button = button;
    }

    public String getButton() {
        return button;
    }
}
