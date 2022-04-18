package com.drypalm.easybusiness.repository;

import com.drypalm.easybusiness.model.stock.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
