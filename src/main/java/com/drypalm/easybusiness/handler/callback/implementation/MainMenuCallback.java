package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class MainMenuCallback implements TelegramCallback {
    private final KeyboardFactory keyboard;
    private static final String TEXT = "MAIN MENU";
    private static final String MAIN = "main";

    public MainMenuCallback(KeyboardFactory keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();

        return EditMessageText.builder().chatId(chatId)
                .messageId(messageId).text(TEXT)
                .replyMarkup(keyboard.getService(KeyboardType.MENU).getKeyboard(MAIN)).build();
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.MAIN;
    }
}
