package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.keyboard.MainButtons;
import com.drypalm.easybusiness.saver.SaveCategory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.drypalm.easybusiness.handler.callback.CallbackType.*;

@Component
public class AcceptKeyboard implements KeyboardService {
    private static final String ADD = "add:";

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_ALCOHOL.getCallback().toUpperCase()),
                ADD + SaveCategory.ALCOHOL));

        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_FOOD.getCallback().toUpperCase()),
                ADD + SaveCategory.FOOD));

        buttons.add(ButtonCreator.createButtons(List.of(ACCEPT_SOFT.getCallback().toUpperCase()),
                ADD + SaveCategory.SOFT));

        buttons.add(ButtonCreator.createButtons(List
                .of(MainButtons.BACK.getButton(), MainButtons.MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.MAIN_ACCEPT;
    }
}
