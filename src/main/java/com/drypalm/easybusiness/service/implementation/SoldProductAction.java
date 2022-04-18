package com.drypalm.easybusiness.service.implementation;

import com.drypalm.easybusiness.exception.ServiceException;
import com.drypalm.easybusiness.model.order.SoldProduct;
import com.drypalm.easybusiness.repository.SoldProductRepository;
import com.drypalm.easybusiness.service.SoldProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RED;
import static com.drypalm.easybusiness.model.LoggerColor.TEXT_RESET;

@Slf4j
@Service
public class SoldProductAction implements SoldProductService {
    private static final String CLASS_NAME = "SoldProductService: ";
    private static final String EXPECTED_EXCEPTION = TEXT_RED + "product not found" + TEXT_RESET;

    private final SoldProductRepository repository;

    public SoldProductAction(SoldProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public SoldProduct add(SoldProduct entity) {
        return repository.save(entity);
    }

    @Override
    public SoldProduct getById(long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
    }

    @Override
    public SoldProduct removeById(long id) {
        SoldProduct product = repository.findById(id).orElseThrow(() -> {
            log.warn(CLASS_NAME + EXPECTED_EXCEPTION);
            return new ServiceException(CLASS_NAME + EXPECTED_EXCEPTION);
        });
        repository.delete(product);
        return product;
    }

    @Override
    public List<SoldProduct> index() {
        return repository.findAll();
    }
}
