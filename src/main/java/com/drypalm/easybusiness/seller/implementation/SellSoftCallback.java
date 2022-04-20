package com.drypalm.easybusiness.seller;

import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class SellSoftCallback implements SellByType {
    private final KeyboardFactory keyboard;

    public SellSoftCallback(KeyboardFactory keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        return EditMessageText.builder().chatId(query.getMessage().getChatId().toString())
                .messageId(query.getMessage().getMessageId())
                .replyMarkup(keyboard.getService(KeyboardType.SOFT_CATEGORY).getKeyboard("sell")).build();
    }

    @Override
    public SellType getImplementation() {
        
        return SellType.SOFT;
    }
}
