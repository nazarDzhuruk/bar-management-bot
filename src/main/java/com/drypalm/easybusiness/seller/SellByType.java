package com.drypalm.easybusiness.seller;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface SellByType {
    EditMessageText editMessage(CallbackQuery query);

    SellType getImplementation();
}
