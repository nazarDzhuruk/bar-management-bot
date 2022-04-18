package com.drypalm.easybusiness.repository;

import com.drypalm.easybusiness.model.stock.SoftDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftDrinkRepository extends JpaRepository<SoftDrink, Long> {
}
