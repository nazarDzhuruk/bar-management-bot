package com.drypalm.easybusiness.handler.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface TelegramMessage {

    SendMessage sendMessage(Message message);

    MessageCommand getImplementation();
}
