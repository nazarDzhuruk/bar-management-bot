package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.model.Employee;
import com.drypalm.easybusiness.model.LoggerColor;
import com.drypalm.easybusiness.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
@Component
public class RegisterMessage implements TelegramMessage {
    private final EmployeeService employeeService;
    private static final String CLASS_NAME = "RegisterMessage: ";
    private static final String ACCESS = "accessed";
    private static final String STARTER_USERNAME = "empty";
    private static final String ROLE = "employee";
    private static final String MESSAGE_IF = "this id is already taken!";
    private static final String MESSAGE_ELSE = " SUCCESSFULLY ADDED";


    public RegisterMessage(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        log.trace(CLASS_NAME + ACCESS);
        String chatId = message.getChatId().toString();

        String msg = message.getText().replace("/r ", "");

        if (Boolean.TRUE.equals(employeeService.uniqueIdExist(msg))) {
            log.info(CLASS_NAME + MESSAGE_IF);
            return SendMessage.builder().chatId(chatId).text(MESSAGE_IF).build();
        } else {

            employeeService.add(Employee.builder().username(STARTER_USERNAME).uniqueId(msg)
                    .role(ROLE).status(false).build());
            log.info(CLASS_NAME + ROLE + LoggerColor.TEXT_GREEN + MESSAGE_ELSE + LoggerColor.TEXT_RESET);
            return SendMessage.builder().chatId(chatId).text(MESSAGE_ELSE).build();
        }
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.REGISTER;
    }
}
