package com.drypalm.easybusiness.controller;

import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.repository.SoftDrinkRepository;
import com.drypalm.easybusiness.repository.StockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockRepository repository;
    private final SoftDrinkRepository softDrinkRepository;

    public StockController(SoftDrinkRepository softDrinkRepository, StockRepository repository) {
        this.softDrinkRepository = softDrinkRepository;
        this.repository = repository;
    }

    @GetMapping("/all")
    public List<Stock> getAll() {
        return repository.findAll();
    }

    @PostMapping("/add")
    public void addSoft(@RequestBody SoftDrink drink) {
        List<Stock> stocks = repository.findAll();
        if (stocks.isEmpty()) {
            Stock stock = new Stock();
            stocks.add(stock);
            repository.save(stock);
        }
        drink.setSoftStock(stocks.get(0));
        softDrinkRepository.save(drink);
    }

    @PutMapping("/get/{id}/{quantity}")
    public void sell(@PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity) {
        Stock stocks = repository.findAll().get(0);
        SoftDrink softDrink = stocks.getSoftDrinkSet().stream()
                .filter(s -> s.getProductCode() == id).findAny().orElseThrow();
        stocks.getSoftDrinkSet().remove(softDrink);

        softDrink.setQuantityBottle(softDrink.getQuantityBottle() - quantity);

        stocks.getSoftDrinkSet().add(softDrink);
        repository.save(stocks);
    }
}
