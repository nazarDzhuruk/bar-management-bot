package com.drypalm.easybusiness.handler.callback;

public enum CallbackType {
    SELL("sell"), ADD("add"), ACCEPT("accept"), UPDATE("update"),
    ALCOHOL("alcohol"), SOFT("soft"), COCKTAIL("cocktail"), MAIN("main menu");

    private final String callback;

    CallbackType(String callback) {
        this.callback = callback;
    }

    public String getCallback() {
        return callback;
    }
}
