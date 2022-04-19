package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.handler.callback.CallbackType;
import com.drypalm.easybusiness.keyboard.KeyboardType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.drypalm.easybusiness.keyboard.MainButtons.BACK;
import static com.drypalm.easybusiness.keyboard.MainButtons.MAIN_MENU;

@Component
public class SellKeyboardService implements KeyboardService {
    private static final String ALCOHOL = "ALCOHOL";
    private static final String SOFT = "SOFT";
    private static final String COCKTAIL = "COCKTAIL";

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        buttons.add(ButtonCreator.createButtons(
                List.of(CallbackType.SELL_ALCOHOL.getCallback(),
                        CallbackType.SELL_SOFT.getCallback(), CallbackType.SELL_COCKTAIL.getCallback()), callback));

        buttons.add(ButtonCreator.createButtons(List.of(BACK.getButton(),
                MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.SELL;
    }
}
