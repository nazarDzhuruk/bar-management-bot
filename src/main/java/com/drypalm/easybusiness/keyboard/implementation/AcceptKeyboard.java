package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.keyboard.MainButtons;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.drypalm.easybusiness.handler.callback.CallbackType.*;

@Component
public class AcceptKeyboard implements KeyboardService {

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_ALCOHOL.getCallback().toUpperCase()), callback));
        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_FOOD.getCallback().toUpperCase()), callback));
        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_SOFT.getCallback().toUpperCase()), callback));
        buttons.add(ButtonCreator.createButtons(List
                .of(MainButtons.BACK.getButton(), MainButtons.MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.MAIN_ACCEPT;
    }
}
