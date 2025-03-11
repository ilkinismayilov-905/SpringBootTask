package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralRepository<T,K> {
    T delete(T entity);
    void save(T entity);
    T getById(K id);
    List<K> getAll();
}
