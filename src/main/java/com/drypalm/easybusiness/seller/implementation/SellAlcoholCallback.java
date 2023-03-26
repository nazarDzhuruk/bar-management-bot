package com.drypalm.easybusiness.seller.implementation;

import com.drypalm.easybusiness.keyboard.MainButtons;
import com.drypalm.easybusiness.keyboard.implementation.ButtonCreator;
import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.seller.SellByType;
import com.drypalm.easybusiness.seller.SellType;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellAlcoholCallback implements SellByType {
    private final StockService stockService;
    private static final String COLON = ":";


    public SellAlcoholCallback(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public EditMessageText editMessage(CallbackQuery query) {
        String chatId = query.getMessage().getChatId().toString();
        int messageId = query.getMessage().getMessageId();
        String alcoholType = query.getData().split(COLON)[0];

        List<AlcoholDrink> alcoholByType = stockService.getMainStock().getAlcoholDrinkSet()
                .stream().filter(a -> a.getType().equals(alcoholType)).collect(Collectors.toList());
        List<List<InlineKeyboardButton>> buttons = alcoholByType
                .stream().map(a -> ButtonCreator.createButtons(List.of(a.getName(),
                        String.valueOf(a.getLitre())), "t")).collect(Collectors.toList());

        buttons.add(ButtonCreator.createButtons(List
                .of(MainButtons.BACK.getButton(), MainButtons.MAIN_MENU.getButton()), "sell_alcohol"));

        return EditMessageText.builder()
                .chatId(chatId).messageId(messageId).text("choose: ")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build()).build();
    }

    @Override
    public SellType getImplementation() {
        return SellType.ALCOHOL;
    }
}
