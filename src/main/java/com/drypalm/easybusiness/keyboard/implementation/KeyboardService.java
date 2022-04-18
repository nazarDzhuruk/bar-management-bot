package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardService {

    InlineKeyboardMarkup getKeyboard(String callback);

    KeyboardType getImplementation();
}
