package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.drypalm.easybusiness.handler.message.MessageCommand.*;

@Component
public class HelpMessage implements TelegramMessage {
    private static final String MESSAGE = "Supported commands: ";
    private static final String COLON_SPACE = ": ";
    private static final String REGEX = "\r\n\n";

    @Override
    public SendMessage sendMessage(Message message) {
        String chatId = message.getChatId().toString();

        return SendMessage.builder().chatId(chatId).text(MESSAGE + REGEX +

                START.getCommand() + COLON_SPACE + START.getDescription() + REGEX +

                HELP.getCommand() + COLON_SPACE + HELP.getDescription() + REGEX +

                LOGIN.getCommand() + COLON_SPACE + LOGIN.getDescription() + REGEX +

                LOGOUT.getCommand() + COLON_SPACE + LOGOUT.getDescription()).build();
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.HELP;
    }
}
