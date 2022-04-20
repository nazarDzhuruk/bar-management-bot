package com.drypalm.easybusiness.saver.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.saver.SaveCategory;
import com.drypalm.easybusiness.saver.Saver;
import com.drypalm.easybusiness.service.SoftDrinkService;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class AcceptSoftMessage implements Saver {
    private final StockService stockService;
    private final SoftDrinkService softDrinkService;
    private static final String REGEX = "-";

    public AcceptSoftMessage(StockService stockService, SoftDrinkService softDrinkService) {
        this.stockService = stockService;
        this.softDrinkService = softDrinkService;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        String chatId = message.getChatId().toString();

        String[] caughtMessage = message.getText().split(":");
        String[] product = caughtMessage[1].split("-");

        if (stockService.index().isEmpty()) {
            Stock stock = new Stock();
            stockService.add(stock);
        }
        softDrinkService.add(SoftDrink.builder().name(product[0]).quantityBottle(Integer.parseInt(product[1]))
                .litre(Float.parseFloat(product[2])).softStock(stockService.getMainStock()).build());
        return SendMessage.builder().chatId(chatId).text("Success").build();
    }

    @Override
    public SaveCategory getImplementation() {
        return SaveCategory.SOFT;
    }
}
