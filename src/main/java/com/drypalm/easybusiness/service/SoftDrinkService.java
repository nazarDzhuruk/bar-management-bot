package com.drypalm.easybusiness.service;

import com.drypalm.easybusiness.model.stock.SoftDrink;

public interface SoftDrinkService extends UtilityService<SoftDrink> {
    SoftDrink changeBottleQuantity(long id, int quantity);

    SoftDrink changeLitre(long id, float litre);
}
