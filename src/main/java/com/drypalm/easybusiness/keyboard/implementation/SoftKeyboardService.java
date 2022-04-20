package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.keyboard.MainButtons;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoftKeyboardService implements KeyboardService {
    private final StockService stockService;

    public SoftKeyboardService(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = stockService.getMainStock().getSoftDrinkSet().stream()
                .map(s -> ButtonCreator.createButtons(List.of(s.getName()), String.valueOf(s.getLitre())))
                .collect(Collectors.toList());

        buttons.add(ButtonCreator.createButtons(List.of(MainButtons.BACK.getButton(),
                MainButtons.MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.SOFT_CATEGORY;
    }
}
