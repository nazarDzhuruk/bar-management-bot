package com.drypalm.easybusiness.handler.callback.implementation;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class SellCallback implements TelegramCallback {
    private final KeyboardFactory keyboard;
    private final EmployeeService employeeService;
    private static final String TEXT = "choose option";
    private static final String PREVIOUS_PAGE = "main menu";
    private static final String MESSAGE_IF = "You are not logged in!";

    public SellCallback(EmployeeService employeeService, KeyboardFactory keyboard) {
        this.employeeService = employeeService;
        this.keyboard = keyboard;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {

        String chatId = query.getMessage().getChatId().toString();
        String username = query.getMessage().getChat().getUserName();
        int messageId = query.getMessage().getMessageId();

        if (employeeService.findEmployeeByUsername(username) == null ||
                !employeeService.findEmployeeByUsername(username).isStatus()) {

            return EditMessageText.builder().chatId(chatId)
                    .messageId(messageId).text(MESSAGE_IF).build();

        } else return EditMessageText.builder().chatId(chatId)
                .messageId(messageId).text(TEXT)
                .replyMarkup(keyboard.getService(KeyboardType.SELL).getKeyboard(PREVIOUS_PAGE)).build();

    }

    @Override
    public CallbackType getImplementation() {
        return CallbackType.SELL;
    }
}
