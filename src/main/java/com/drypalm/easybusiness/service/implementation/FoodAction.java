package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.stock.Food;
import com.drypalm.easybusiness.repository.FoodRepository;
import com.drypalm.easybusiness.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class FoodAction implements FoodService {
    private static final String CLASS_NAME = "FoodService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "food not found" + TEXT_RESET;

    private final FoodRepository repository;

    public FoodAction(FoodRepository repository) {
        this.repository = repository;
    }

    @Override
    public Food add(Food entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public Food getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
    }

    @Override
    public Food removeById(long id) {
        Food food = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
                    return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
                });
        repository.delete(food);
        return food;
    }

    @Override
    public List<Food> index() {
        return repository.findAll();
    }

    @Override
    public Food changeQuantity(long id, int quantity) {
        Food food = repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
        food.setQuantity(quantity);
        repository.save(food);
        return food;
    }
}
