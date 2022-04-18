package com.drypalm.easybusiness.handler;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.handler.message.MessageCommand;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class Handler implements HandlerService {
    private final List<TelegramMessage> message;
    private final List<TelegramCallback> callback;

    private Map<MessageCommand, TelegramMessage> messageMap;
    private Map<CallbackType, TelegramCallback> callbackMap;

    public Handler(List<TelegramCallback> callback, List<TelegramMessage> message) {
        this.callback = callback;
        this.message = message;
    }

    @PostConstruct
    private void init() {
        messageMap = new EnumMap<>(MessageCommand.class);
        callbackMap = new EnumMap<>(CallbackType.class);

        message.forEach(m -> messageMap.put(m.getImplementation(), m));
        callback.forEach(c -> callbackMap.put(c.getImplementation(), c));
    }

    @Override
    public TelegramMessage processMessage(MessageCommand command) {
        return Optional.ofNullable(messageMap.get(command)).orElseThrow();
    }

    @Override
    public TelegramCallback processCallback(CallbackType type) {
        return Optional.ofNullable(callbackMap.get(type)).orElseThrow();
    }
}
