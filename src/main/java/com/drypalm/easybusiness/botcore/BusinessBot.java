package com.drypalm.easybusiness.botcore;

import com.drypalm.easybusiness.handler.HandlerService;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.model.Employee;
import com.drypalm.easybusiness.service.EmployeeService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class BusinessBot extends TelegramLongPollingBot {

    private final BotConfiguration configuration;
    private final HandlerService handlerService;
    private final EmployeeService employeeService;

    public BusinessBot(EmployeeService employeeService, HandlerService handlerService, BotConfiguration configuration) {
        this.employeeService = employeeService;
        this.handlerService = handlerService;
        this.configuration = configuration;
    }

    @PostConstruct
    public void init() {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);

        } catch (TelegramApiException e) {

        }
    }

    @Override
    public String getBotUsername() {
        return configuration.getUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {

        if (update.getMessage() != null && update.getMessage().hasText()) {

            String msg = update.getMessage().getText();

            if (msg.equals("exit")) {
                List<Employee> index = employeeService.index();
                index.forEach(e -> e.setStatus(false));
                index.forEach(employeeService::add);
                System.exit(0);
            }
            MessageCommand command = Arrays.stream(MessageCommand.values())
                    .filter(c -> msg.startsWith(c.getCommand())).findAny().orElseThrow();

            execute(handlerService.processMessage(command)
                    .sendMessage(update.getMessage()));

        } else if (update.hasCallbackQuery()) {

            String callback;

            if (update.getCallbackQuery().getData().split(":")[0].equals("<<")) {
                callback = update.getCallbackQuery().getData().split(":")[1];
            } else {
                callback = update.getCallbackQuery().getData().split(":")[0];
            }

            CallbackType callbackType = Arrays.stream(CallbackType.values())
                    .filter(c -> c.getCallback().equals(callback.toLowerCase())).findAny().orElseThrow();

            execute(handlerService.processCallback(callbackType)
                    .editMessage(update.getCallbackQuery()));
        }

    }
}
