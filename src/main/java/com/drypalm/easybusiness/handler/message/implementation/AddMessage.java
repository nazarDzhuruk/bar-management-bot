package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.saver.SaveCategory;
import com.drypalm.easybusiness.saver.SaverFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Locale;

@Component
public class AddMessage implements TelegramMessage {
    private final SaverFactory saverFactory;

    public AddMessage(SaverFactory saverFactory) {
        this.saverFactory = saverFactory;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        String[] caughtMessage = message.getText().split(":");
        String saverType = caughtMessage[0];
        return saverFactory.save(SaveCategory.valueOf(saverType.toUpperCase(Locale.ROOT))).sendMessage(message);
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.ADD;
    }
}
