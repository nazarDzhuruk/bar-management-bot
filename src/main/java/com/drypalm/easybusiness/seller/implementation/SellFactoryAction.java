package com.drypalm.easybusiness.seller.implementation;

import com.drypalm.easybusiness.exception.HandlerException;
import com.drypalm.easybusiness.seller.SellByType;
import com.drypalm.easybusiness.seller.SellFactory;
import com.drypalm.easybusiness.seller.SellType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class SellFactoryAction implements SellFactory {
    private final List<SellByType> sellByTypeList;
    private Map<SellType, SellByType> sellByTypeMap;

    public SellFactoryAction(List<SellByType> sellByTypeList) {
        this.sellByTypeList = sellByTypeList;
    }

    @PostConstruct
    private void init() {
        sellByTypeMap = new EnumMap<>(SellType.class);
        sellByTypeList.forEach(t -> sellByTypeMap.put(t.getImplementation(), t));
    }

    @Override
    public SellByType sell(SellType type) {
        return Optional.ofNullable(sellByTypeMap.get(type)).orElseThrow(HandlerException::new);
    }
}
