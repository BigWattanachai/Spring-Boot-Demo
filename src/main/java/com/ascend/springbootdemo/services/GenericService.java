package com.ascend.springbootdemo.services;

/**
 * Created by BiG on 6/25/2017 AD.
 */
public interface GenericService<T> {
    T getById(Long id);

    T edit(Long id, T obj);

    T delete(Long id);
}
