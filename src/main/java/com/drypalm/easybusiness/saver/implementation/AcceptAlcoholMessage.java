package com.drypalm.easybusiness.handler.message.implementation;

import com.drypalm.easybusiness.handler.message.MessageCommand;
import com.drypalm.easybusiness.handler.message.TelegramMessage;
import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.service.AlcoholDrinkService;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class AcceptAlcoholMessage implements TelegramMessage {
    private final StockService stockService;
    private final AlcoholDrinkService alcoholDrinkService;

    public AcceptAlcoholMessage(StockService stockService, AlcoholDrinkService alcoholDrinkService) {
        this.stockService = stockService;
        this.alcoholDrinkService = alcoholDrinkService;
    }

    @Override
    public SendMessage sendMessage(Message message) {
        String chatId = message.getChatId().toString();
        String[] product = message.getText().split("-");

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
    public MessageCommand getImplementation() {
        return MessageCommand.ADD_ALCOHOL;
    }
}
