package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.repository.SoftDrinkRepository;
import com.drypalm.easybusiness.service.SoftDrinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class SoftDrinkAction implements SoftDrinkService {
    private static final String CLASS_NAME = "SoftDrinkService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "drink not found" + TEXT_RESET;

    private final SoftDrinkRepository repository;

    public SoftDrinkAction(SoftDrinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public SoftDrink add(SoftDrink entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public SoftDrink getById(long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
    }

    @Override
    public SoftDrink removeById(long id) {
        SoftDrink drink = repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
        repository.delete(drink);
        return drink;
    }

    @Override
    public List<SoftDrink> index() {
        return repository.findAll();
    }

    @Override
    public SoftDrink changeBottleQuantity(long id, int quantity) {
        SoftDrink drink = repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
        drink.setQuantityBottle(quantity);
        repository.save(drink);
        return drink;
    }

    @Override
    public SoftDrink changeLitre(long id, float litre) {
        SoftDrink drink = repository.findById(id).orElseThrow();
        drink.setLitre(litre);
        repository.save(drink);
        return drink;
    }
}
