package com.drypalm.easybusiness.service;

import com.drypalm.easybusiness.model.stock.Food;

public interface FoodService extends UtilityService<Food> {
    Food changeQuantity(long id, int quantity);
}
