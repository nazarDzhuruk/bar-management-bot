package com.drypalm.easybusiness.service;

import com.drypalm.easybusiness.model.stock.AlcoholDrink;

public interface AlcoholDrinkService extends UtilityService<AlcoholDrink> {
    AlcoholDrink changeBottleQuantity(long id, int quantity);

    AlcoholDrink changeLitre(long id, float litre);
}
