package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class AddCallback implements TelegramCallback {
    private final KeyboardFactory keyboard;
    private static final String PREVIOUS_PAGE = "main menu";
    private static final String TEXT = "choose option \r\n" + "\n"
            + "accept: adds a new product \r\n" + "\n"
            + "update: updates exist product";

    public AddCallback(KeyboardFactory keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();

        return EditMessageText.builder().chatId(chatId)
                .messageId(messageId).text(TEXT)
                .replyMarkup(keyboard.getService(KeyboardType.ADD).getKeyboard(PREVIOUS_PAGE)).build();
    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.ADD;
    }
}
