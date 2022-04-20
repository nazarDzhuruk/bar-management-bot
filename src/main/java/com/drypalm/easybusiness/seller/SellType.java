package com.drypalm.easybusiness.seller;

public enum SellType {
    ALCOHOL("alcohol"),
    SELL_SOFT("soft"),
    COCKTAIL("cocktail");
    private final String callbackType;

    SellType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getCallbackType() {
        return callbackType;
    }
}
