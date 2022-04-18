package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class AlcoholCallback implements TelegramCallback {
    private final KeyboardFactory keyboard;
    private final StockService stockService;
    private static final String PREVIOUS_PAGE = "sell";
    private static final String REGEX = "\r\n\r\n";

    public AlcoholCallback(StockService stockService, KeyboardFactory keyboard) {
        this.stockService = stockService;
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();

        return EditMessageText.builder().chatId(chatId)
                .messageId(messageId).text("choose:")
                .replyMarkup(keyboard.getService(KeyboardType.ALCOHOL_CATEGORY).getKeyboard(PREVIOUS_PAGE)).build();

    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.ALCOHOL;
    }
}
