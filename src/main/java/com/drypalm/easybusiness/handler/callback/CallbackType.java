package com.drypalm.easybusiness.handler.callback;

public enum CallbackType {
    SELL("sell"), ADD("add"), MAIN_ACCEPT("main_accept"), UPDATE("update"),
    SELL_ALCOHOL("sell_alcohol"), SELL_SOFT("sell_soft"), SELL_COCKTAIL("sell_cocktail"), MAIN("main menu"),
    ACCEPT_ALCOHOL("accept alcohol"), ACCEPT_SOFT("accept soft"), ACCEPT_FOOD("accept food"), TYPE("type");

    private final String callback;

    CallbackType(String callback) {
        this.callback = callback;
    }

    public String getCallback() {
        return callback;
    }
}
