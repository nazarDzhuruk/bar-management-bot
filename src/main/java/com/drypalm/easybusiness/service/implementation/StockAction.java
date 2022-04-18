package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.model.stock.Food;
import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.repository.StockRepository;
import com.drypalm.easybusiness.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class StockAction implements StockService {
    private static final String CLASS_NAME = "StockService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "stock not found" + TEXT_RESET;

    private final StockRepository repository;

    public StockAction(StockRepository repository) {
        log.trace(CLASS_NAME);
        this.repository = repository;
    }

    @Override
    public Stock add(Stock entity) {
        return repository.save(entity);
    }

    @Override
    public Stock getById(long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(EXPECTED_EXCEPTION);
        });
    }

    @Override
    public Stock removeById(long id) {
        Stock stock = repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
        repository.delete(stock);
        return stock;
    }

    @Override
    public List<Stock> index() {
        return repository.findAll();
    }

    @Override
    public Stock updateAlcohol(AlcoholDrink alcoholDrink) {
        Stock stock = repository.findAll().get(0);
        AlcoholDrink drink = stock.getAlcoholDrinkSet().stream()
                .filter(a -> a.getProductCode() == alcoholDrink.getProductCode()).findFirst().orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        stock.getAlcoholDrinkSet().remove(drink);
        stock.getAlcoholDrinkSet().add(alcoholDrink);
        repository.save(stock);
        return stock;
    }

    @Override
    public Stock updateSoft(SoftDrink softDrink) {
        Stock stock = repository.findAll().get(0);
        SoftDrink drink = stock.getSoftDrinkSet().stream()
                .filter(s -> s.getProductCode() == softDrink.getProductCode()).findFirst().orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        stock.getSoftDrinkSet().remove(drink);
        stock.getSoftDrinkSet().add(softDrink);
        repository.save(stock);
        return stock;
    }

    @Override
    public Stock updateFood(Food food) {
        Stock stock = repository.findAll().get(0);
        Food stockFood = stock.getFoodSet().stream().filter(f -> f.getProductCode() == food.getProductCode())
                .findFirst().orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        stock.getFoodSet().remove(stockFood);
        stock.getFoodSet().add(food);
        repository.save(stock);
        return stock;
    }

    @Override
    public Stock getMainStock() {
        return repository.findAll().get(0);
    }
}