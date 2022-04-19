package com.drypalm.easybusiness.botcore;

import com.drypalm.easybusiness.handler.HandlerService;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.message.MessageCommand;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Locale;

@Component
public class BusinessBot extends TelegramLongPollingBot {

    private final BotConfiguration configuration;
    private final HandlerService handlerService;
    private boolean switchToMsg = false;

    public BusinessBot(HandlerService handlerService, BotConfiguration configuration) {
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

        if (update.getMessage() != null && update.getMessage().hasText() || switchToMsg) {

            String msg = update.getMessage().getText();
            System.out.println(switchToMsg);

            if (msg.equals("/stop")) {
                switchToMsg = false;
                execute(SendMessage.builder().chatId(update.getMessage()
                        .getChatId().toString()).text("Stop").build());
                return;
            }
            if (switchToMsg) {

                handlerService.processMessage(MessageCommand.ADD_ALCO).sendMessage(update.getMessage());

                execute(SendMessage.builder().chatId(update.getMessage()
                        .getChatId().toString()).text(update.getMessage().getText()).build());
                return;
            }

            MessageCommand command = Arrays.stream(MessageCommand.values())
                    .filter(m -> m.getCommand().equals(msg.split(" ")[0])).findFirst().orElseThrow();

            execute(handlerService.processMessage(command).sendMessage(update.getMessage()));

        } else if (update.hasCallbackQuery()) {
            String callback;

            if (update.getCallbackQuery().getData().split(":")[0].equals("<<")) {
                callback = update.getCallbackQuery().getData().split(":")[1];
            } else if (update.getCallbackQuery().getData().split(":")[1].equals("type")) {
                callback = update.getCallbackQuery().getData().split(":")[1];
            } else {
                callback = update.getCallbackQuery().getData().split(":")[0];
            }

            System.out.println(callback);

            if (callback.toLowerCase(Locale.ROOT).startsWith("accept ")) {
                switchToMsg = true;
            }

            CallbackType callbackType = Arrays.stream(CallbackType.values())
                    .filter(c -> c.getCallback().equals(callback.toLowerCase())).findAny().orElseThrow();

            execute(handlerService.processCallback(callbackType)
                    .editMessage(update.getCallbackQuery()));

        }
    }
}
