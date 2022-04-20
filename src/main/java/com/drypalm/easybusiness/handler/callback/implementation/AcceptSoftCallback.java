package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class AcceptSoftCallback implements TelegramCallback {
    private static final String MESSAGE = "FORMAT: \r\n\r" + "name-bottles-litres";

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        return EditMessageText.builder().chatId(query.getMessage().getChatId().toString())
                .messageId(query.getMessage().getMessageId()).text(MESSAGE).build();
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.ACCEPT_SOFT;
    }
}
