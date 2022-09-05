package com.gkhy.servicebase.service.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IService<T, E extends Number> {

    Optional<T> findOne(Specification<T> spec);

    List<T> findAll(Specification<T> spec);

    Page<T> findAll(Specification<T> spec, Pageable pageable);

    List<T> findAll(Specification<T> spec, Sort sort);

    List<T> findAllById(Iterable<E> ids);

    long count(Specification<T> spec);

    boolean exists(Specification<T> spec);

    List<T> findAll();

    List<T> findAll(Sort sort);

    <S extends T> List<S> findAll(Example<S> example);

    <S extends T> List<S> findAll(Example<S> example, Sort sort);

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(E e);

    boolean existsById(E e);

    long count();

    void deleteById(E e);

    void delete(T entity);

    void deleteAllById(Iterable<? extends E> es);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    <S extends T> Optional<S> findOne(Example<S> example);

    <S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends T> long count(Example<S> example);

    <S extends T> boolean exists(Example<S> example);

    void removeById(E e);

    <S extends T> S saveAndFlush(S entity);

    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);
}
