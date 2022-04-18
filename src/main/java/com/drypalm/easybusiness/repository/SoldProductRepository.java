package com.drypalm.easybusiness.repository;

import com.drypalm.easybusiness.model.order.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldProductRepository extends JpaRepository<SoldProduct, Long> {
}
