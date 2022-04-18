package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.repository.AlcoholDrinkRepository;
import com.drypalm.easybusiness.service.AlcoholDrinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class AlcoholDrinkAction implements AlcoholDrinkService {
    private static final String CLASS_NAME = "AlcoholDrinkService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "Alcohol drink not found" + TEXT_RESET;

    private final AlcoholDrinkRepository repository;

    public AlcoholDrinkAction(AlcoholDrinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public AlcoholDrink add(AlcoholDrink entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public AlcoholDrink getById(long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
    }

    @Override
    public AlcoholDrink removeById(long id) {
        AlcoholDrink drink = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        repository.delete(drink);
        return drink;
    }


    @Override
    public List<AlcoholDrink> index() {
        return repository.findAll();
    }

    @Override
    public AlcoholDrink changeBottleQuantity(long id, int quantity) {
        AlcoholDrink drink = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        drink.setQuantityBottle(quantity);
        repository.save(drink);
        return drink;
    }

    @Override
    public AlcoholDrink changeLitre(long id, float litre) {
        AlcoholDrink drink = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        drink.setLitre(litre);
        repository.save(drink);
        return drink;
    }
}
