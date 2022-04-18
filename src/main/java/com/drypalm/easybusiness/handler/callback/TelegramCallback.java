package com.drypalm.easybusiness.handler.callback;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface TelegramCallback {

    EditMessageText editMessage(CallbackQuery query);

    CallbackType getImplementation();
}
