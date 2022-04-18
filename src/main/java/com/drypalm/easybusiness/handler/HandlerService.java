package com.drypalm.easybusiness.handler;

import com.drypalm.easybusiness.handler.callback.TelegramCallback;
import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.handler.message.MessageCommand;

public interface HandlerService {
    TelegramMessage processMessage(MessageCommand command);
    TelegramCallback processCallback(CallbackType type);
}
