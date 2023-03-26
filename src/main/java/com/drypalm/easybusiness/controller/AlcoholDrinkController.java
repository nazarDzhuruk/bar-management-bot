package com.drypalm.easybusiness.controller;

import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.service.AlcoholDrinkService;
import com.drypalm.easybusiness.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alcohol")
public class AlcoholDrinkController {
    private final StockService stockService;
    private final AlcoholDrinkService drinkService;

    public AlcoholDrinkController(StockService stockService, AlcoholDrinkService drinkService) {
        this.stockService = stockService;
        this.drinkService = drinkService;
    }

    @GetMapping("/all")
    public List<AlcoholDrink> getAll() {
        return drinkService.index();
    }

    @PostMapping("/add")
    public void addAlco(@RequestBody AlcoholDrink drink) {
        Stock stock = new Stock();
        stockService.add(stock);

        drink.setAlcoholStock(stock);

        drinkService.add(drink);
    }

}
