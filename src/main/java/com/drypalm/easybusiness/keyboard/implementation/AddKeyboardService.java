package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.keyboard.MainButtons;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddKeyboardService implements KeyboardService {
    private static final String ACCEPT = "MAIN_ACCEPT";
    private static final String UPDATE = "UPDATE";

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT, UPDATE), callback));

        buttons.add(ButtonCreator.createButtons(List.of(MainButtons.BACK.getButton(),
                MainButtons.MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.ADD;
    }
}
