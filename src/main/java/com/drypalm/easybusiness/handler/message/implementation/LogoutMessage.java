package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.model.Employee;
import com.drypalm.easybusiness.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class LogoutMessage implements TelegramMessage {
    private final EmployeeService employeeService;
    private static final String LOGOUT = " logout";

    public LogoutMessage(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        String chatId = message.getChatId().toString();
        String username = message.getChat().getUserName();

        Employee employee = employeeService.findEmployeeByUsername(username);

        if (employee.isStatus()) {
            employee.setStatus(false);
            employeeService.add(employee);
        }
        return SendMessage.builder().chatId(chatId).text(username + LOGOUT).build();
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.LOGOUT;
    }
}
