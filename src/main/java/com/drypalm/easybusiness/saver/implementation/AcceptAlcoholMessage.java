package com.drypalm.easybusiness.saver.implementation;

import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.saver.SaveCategory;
import com.drypalm.easybusiness.saver.Saver;
import com.drypalm.easybusiness.service.AlcoholDrinkService;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class AcceptAlcoholMessage implements Saver {
    private final StockService stockService;
    private final AlcoholDrinkService alcoholDrinkService;

    public AcceptAlcoholMessage(StockService stockService, AlcoholDrinkService alcoholDrinkService) {
        this.stockService = stockService;
        this.alcoholDrinkService = alcoholDrinkService;
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
        AlcoholDrink drink = AlcoholDrink.builder().type(product[0]).name(product[1])
                .quantityBottle(Integer.parseInt(product[2])).litre(Float.parseFloat(product[3]))
                .stock(stockService.getMainStock()).build();

        alcoholDrinkService.add(drink);

        return SendMessage.builder().chatId(chatId).text("Success").build();
    }

    @Override
    public SaveCategory getImplementation() {
        return SaveCategory.ALCOHOL;
    }
}
