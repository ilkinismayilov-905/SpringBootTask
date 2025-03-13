package org.example.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T,K> {
    void deleteById(T id);
    T save(Object entity);
    Optional<T> getById(K id);
    List<T> getAll();
}
