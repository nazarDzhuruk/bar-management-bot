package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class AcceptAlcoholCallback implements TelegramCallback {
    private static final String MESSAGE = "FORMAT: \r\n\r" + "name-bottles-litres";

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();

        return EditMessageText.builder().chatId(chatId).messageId(messageId).text(MESSAGE).build();
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.ACCEPT_ALCOHOL;
    }
}
