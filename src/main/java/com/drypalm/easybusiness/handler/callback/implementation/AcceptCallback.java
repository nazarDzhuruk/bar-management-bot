package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class AcceptCallback implements TelegramCallback {
    private final KeyboardFactory keyboard;
    private static final String PREVIOUS_PAGE = "add";
    private static final String TEXT = "accepting goods \n\r\n" + "enter product code";

    public AcceptCallback(KeyboardFactory keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();

        return EditMessageText.builder().chatId(chatId)
                .messageId(messageId).text(TEXT)
                .replyMarkup(keyboard.getService(KeyboardType.SELL).getKeyboard(PREVIOUS_PAGE)).build();
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.ACCEPT;
    }
}
