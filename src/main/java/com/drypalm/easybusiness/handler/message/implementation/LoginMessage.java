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
public class LoginMessage implements TelegramMessage {
    private final EmployeeService employeeService;
    private static final String CLASS_NAME = "LoginMessage: ";
    private static final String EMPTY_USER = "empty";
    private static final String REGEX = "\r\n\r\n";
    private static final String HELLO = "Hello, ";
    private static final String SUCCESS = "successfully";
    private static final String MESSAGE_IF = "Type /start to open menu";
    private static final String MESSAGE_IF_ELSE = "user not exist!";
    private static final String MESSAGE_ELSE = "incorrect id";

    public LoginMessage(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public SendMessage sendMessage(Message message) {

        String chatId = message.getChatId().toString();
        String username = message.getChat().getUserName();
        String uniqueId = message.getText().replace("/li ", "");

        if (Boolean.TRUE.equals(employeeService.uniqueIdExist(uniqueId))) {

            Employee employee = employeeService.findEmployeeByUniqueId(uniqueId);
            if (employee.getUsername().equals(username)) {
                employee.setStatus(true);
                employeeService.add(employee);

                log.info(CLASS_NAME + LoggerColor.TEXT_GREEN + SUCCESS + LoggerColor.TEXT_RESET);
                return SendMessage.builder().chatId(chatId).text(HELLO + username + REGEX + MESSAGE_IF).build();
            } else if (employee.getUsername().equals(EMPTY_USER) &&
                    Boolean.TRUE.equals(!employeeService.ifUsernameTaken(username))) {

                employee.setStatus(true);
                employee.setUsername(username);
                employeeService.add(employee);

                log.info(CLASS_NAME + LoggerColor.TEXT_GREEN + SUCCESS + LoggerColor.TEXT_RESET);
                return SendMessage.builder().chatId(chatId).text(HELLO + username + REGEX + MESSAGE_IF).build();
            } else return SendMessage.builder().chatId(chatId).text(MESSAGE_ELSE).build();

        } else {
            log.info(CLASS_NAME + LoggerColor.TEXT_YELLOW + MESSAGE_IF_ELSE + LoggerColor.TEXT_RESET);
            return SendMessage.builder().chatId(chatId).text(MESSAGE_IF_ELSE).build();
        }
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.LOGIN;
    }
}
