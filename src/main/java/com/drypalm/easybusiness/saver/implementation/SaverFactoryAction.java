package com.drypalm.easybusiness.saver.implementation;

import com.drypalm.easybusiness.exception.HandlerException;
import com.drypalm.easybusiness.saver.SaveCategory;
import com.drypalm.easybusiness.saver.Saver;
import com.drypalm.easybusiness.saver.SaverFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class SaverFactoryAction implements SaverFactory {
    private final List<Saver> saverList;
    private Map<SaveCategory, Saver> saverMap;

    public SaverFactoryAction(List<Saver> saverList) {
        this.saverList = saverList;
    }

    @PostConstruct
    private void init(){
        saverMap = new EnumMap<>(SaveCategory.class);
        saverList.forEach(s -> saverMap.put(s.getImplementation(), s));
    }

    @Override
    public Saver save(SaveCategory category) {
        return Optional.ofNullable(saverMap.get(category)).orElseThrow(HandlerException::new);
    }
}
