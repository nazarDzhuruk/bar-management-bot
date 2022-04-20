package com.drypalm.easybusiness.service;

import java.util.List;

public interface UtilityService<T> {

    T add(T entity);

    T getById(long id);

    T removeById(long id);

    List<T> index();

}
