package com.drypalm.easybusiness.service;

import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import com.drypalm.easybusiness.model.stock.Food;
import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.model.stock.Stock;

public interface StockService extends UtilityService<Stock> {
    Stock updateAlcohol(AlcoholDrink alcoholDrink);

    Stock updateSoft(SoftDrink softDrink);

    Stock updateFood(Food food);

    Stock getMainStock();
}
