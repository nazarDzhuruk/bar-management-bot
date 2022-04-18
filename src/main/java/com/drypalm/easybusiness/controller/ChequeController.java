package com.drypalm.easybusiness.controller;

import com.drypalm.easybusiness.model.order.SoldProduct;
import com.drypalm.easybusiness.model.stock.SoftDrink;
import com.drypalm.easybusiness.model.stock.Stock;
import com.drypalm.easybusiness.repository.SoldProductRepository;
import com.drypalm.easybusiness.repository.StockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheque")
public class ChequeController {
    private final SoldProductRepository productRepository;
    private final StockRepository stockRepository;

    public ChequeController(SoldProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping("/order")
    public List<SoldProduct> getProducts() {
        return productRepository.findAll();
    }

    @PutMapping("/sell/{id}/{quantity}/{litre}")
    public void createCheque(@PathVariable("id") Integer id,
                             @PathVariable("quantity") Integer quantity,
                             @PathVariable("litre") Float litre) {

        Stock stock = stockRepository.findAll().get(0);

        SoftDrink softDrink = stock.getSoftDrinkSet().stream()
                .filter(s -> s.getProductCode() == id).findAny().orElseThrow();
        stock.getSoftDrinkSet().remove(softDrink);

        productRepository.save(SoldProduct.builder()
                .name(softDrink.getName()).quantity(quantity).litre(litre).build());

        softDrink.setQuantityBottle(softDrink.getQuantityBottle() - quantity);
        softDrink.setLitre(softDrink.getLitre() - litre);

        stock.getSoftDrinkSet().add(softDrink);
        stockRepository.save(stock);

    }
}
