package com.drypalm.easybusiness.saver;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Saver {

    SendMessage sendMessage(Message message);

    SaveCategory getImplementation();
}
