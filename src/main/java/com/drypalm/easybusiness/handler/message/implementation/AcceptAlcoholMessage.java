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

import java.util.List;

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

        List<Stock> stocks = stockService.index();
        if (stocks.isEmpty()) {
            Stock stock = new Stock();
            stocks.add(stock);
            stockService.add(stock);
        }
        AlcoholDrink drink = AlcoholDrink.builder().name(product[0])
                .quantityBottle(Integer.parseInt(product[1])).litre(Long.parseLong(product[2]))
                .stock(stocks.get(0)).build();

        alcoholDrinkService.add(drink);

        return SendMessage.builder().chatId(chatId).text("Success").build();
    }

    @Override
    public MessageCommand getImplementation() {
        return MessageCommand.ADD_ALCO;
    }
}
