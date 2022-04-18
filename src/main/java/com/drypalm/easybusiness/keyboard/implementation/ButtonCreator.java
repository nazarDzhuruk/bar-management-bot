package com.drypalm.easybusiness.keyboard.implementation;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;

public class ButtonCreator {
    private static final String COLON = ":";

    private ButtonCreator() {
    }

    public static List<InlineKeyboardButton> createButtons(List<String> buttons, String callback) {
        return buttons.stream().map(b -> InlineKeyboardButton.builder()
                        .text(b).callbackData(b + COLON + callback).build())
                .collect(Collectors.toList());
    }
}
