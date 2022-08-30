package com.gkhy.servicebase.service;

import com.gkhy.servicebase.service.repository.IService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class ServiceImpl<T, E extends Number, Repository extends IService<T, E>> {

    protected Repository iRepository;

    public ServiceImpl(Repository iRepository) {
        this.iRepository = iRepository;
    }

    public Optional<T> findOne(Specification<T> spec) {
        return iRepository.findOne(spec);
    }

    public List<T> findAll(Specification<T> spec) {
        return iRepository.findAll(spec);
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return iRepository.findAll(spec, pageable);
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return iRepository.findAll(spec, sort);
    }

    public long count(Specification<T> spec) {
        return iRepository.count(spec);
    }

    public boolean exists(Specification<T> spec) {
        return iRepository.exists(spec);
    }

    public List<T> findAll() {
        return iRepository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return iRepository.findAll(sort);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return iRepository.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return iRepository.findAll(example, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return iRepository.findAll(pageable);
    }

    public Optional<T> findById(E e) {
        return iRepository.findById(e);
    }

    public boolean existsById(E e) {
        return iRepository.existsById(e);
    }

    public long count() {
        return iRepository.count();
    }

    public void deleteById(E e) {
        iRepository.deleteById(e);
    }

    public void delete(T entity) {
        iRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends E> es) {
        iRepository.deleteAllById(es);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        iRepository.deleteAll(entities);
    }

    public void deleteAll() {
        iRepository.deleteAll();
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return iRepository.findOne(example);
    }

    public Page<T> findAll(int current, int limit) {
        Pageable pageable = PageRequest.of(current - 1, limit);
        return iRepository.findAll(pageable);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return iRepository.findAll(example, pageable);
    }

    public <S extends T> long count(Example<S> example) {
        return iRepository.count(example);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return iRepository.exists(example);
    }

    public void removeById(E e) {
        iRepository.deleteById(e);
    }

    public T save(T entity) {
            return iRepository.saveAndFlush(entity);
        }

    public List<T> saveAll(Iterable<T> entities) {
        return iRepository.saveAllAndFlush(entities);
    }

    public T update(Object o, T entity) {
        BeanUtils.copyProperties(o, entity);
        return iRepository.saveAndFlush(entity);
    }

    public List<T> findAllOrderByLimit(Sort.Direction direction, String property, int limit ) {
        Sort sort = Sort.by(direction, property);
        return  new ArrayList<>(iRepository.findAll(sort).subList(0, limit));
    }

    private Specification<T> getSpecification(String columnName, String item) {
        return (root, query, cb) ->  cb.equal(root.get(columnName), item);
    }

    public Optional<T> findByColumnName(String columnName, String item) {
        return iRepository.findOne(getSpecification(columnName, item));
    }

    public Optional<T> findByColumnName(String columnName) {
        return Optional.empty();
    }

    public boolean existsByColumnName(String columnName, String item) {
        return iRepository.exists(getSpecification(columnName, item));
    }
}
