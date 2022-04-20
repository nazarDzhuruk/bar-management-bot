package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.drypalm.easybusiness.keyboard.KeyboardType.ADD;
import static com.drypalm.easybusiness.keyboard.KeyboardType.SELL;

@Component
public class MenuKeyboardService implements KeyboardService {

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(ButtonCreator.createButtons(List.of(SELL.getKeyboard(), ADD.getKeyboard()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.MENU;
    }
}
