package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.seller.SellFactory;
import com.drypalm.easybusiness.seller.SellType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Locale;

@Component
public class SellByTypeCallback implements TelegramCallback {
    private final SellFactory sellFactory;
    private static final String REGEX = ":";

    public SellByTypeCallback(SellFactory sellFactory) {
        this.sellFactory = sellFactory;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String type = query.getData().split(REGEX)[2];
        return sellFactory.sell(SellType.valueOf(type.toUpperCase(Locale.ROOT))).editMessage(query);
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.TYPE;
    }
}
