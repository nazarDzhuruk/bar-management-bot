package com.drypalm.easybusiness.keyboard.implementation;

import com.drypalm.easybusiness.keyboard.KeyboardType;
import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.drypalm.easybusiness.keyboard.MainButtons.BACK;
import static com.drypalm.easybusiness.keyboard.MainButtons.MAIN_MENU;

@Component
public class AlcoholCategoryKeyboardService implements KeyboardService {
    private final StockService stockService;

    public AlcoholCategoryKeyboardService(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public InlineKeyboardMarkup getKeyboard(String callback) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        List<String> alcoholType = stockService.index().get(0)
                .getAlcoholDrinkSet().stream().map(AlcoholDrink::getType).collect(Collectors.toList());

        buttons.add(ButtonCreator.createButtons(alcoholType, callback));
        buttons.add(ButtonCreator.createButtons(List.of(BACK.getButton(), MAIN_MENU.getButton()), callback));

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    @Override
    public KeyboardType getImplementation() {
        return KeyboardType.ALCOHOL_CATEGORY;
    }
}
