package com.drypalm.easybusiness.repository;

import com.drypalm.easybusiness.model.stock.AlcoholDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholDrinkRepository extends JpaRepository<AlcoholDrink, Long> {
}
