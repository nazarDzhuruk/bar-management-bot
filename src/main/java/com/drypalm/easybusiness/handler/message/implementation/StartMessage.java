package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.keyboard.KeyboardFactory;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.model.Employee;
import com.drypalm.easybusiness.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartMessage implements TelegramMessage {
    private final KeyboardFactory factory;
    private final EmployeeService employeeService;
    private static final String REGEX = "\r\n\r\n";

    public StartMessage(EmployeeService employeeService, KeyboardFactory factory) {
        this.employeeService = employeeService;
        this.factory = factory;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        String chatId = message.getChatId().toString();
        String username = message.getChat().getUserName();

        Employee employee = employeeService.index().stream()
                .filter(e -> e.getUsername().equals(username)).findAny().orElseThrow();

        if (employee.isStatus()) {
            return SendMessage.builder().chatId(chatId).text("Hello again, " + username)
                    .replyMarkup(factory.getService(KeyboardType.MENU).getKeyboard("main")).build();
        } else return SendMessage.builder().chatId(chatId).text("You are not logged in." + REGEX +
                "Please enter /li your_unique_id").build();
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.START;
    }
}
