package com.gkhy.servicebase.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gkhy.servicebase.service.repository.IService;

/**
 * abstract ServiceImpl class for all the model service inheriting
 *
 * */
public abstract class ServiceImpl<T, Repository extends IService<T>> {

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

    public List<T> findAllById(Iterable<Long> ids){
        return iRepository.findAllById(ids);
    }

    public Optional<T> findById(Long e) {
        return iRepository.findById(e);
    }

    public boolean existsById(Long e) {
        return iRepository.existsById(e);
    }

    public long count() {
        return iRepository.count();
    }

    public void deleteById(Long e) {
        iRepository.deleteById(e);
    }

    public void delete(T entity) {
        iRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Long> es) {
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

    public void removeById(Long e) {
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

    private Specification<T> getSpecification(String columnName, String value) {
        return (root, query, cb) ->  cb.equal(root.get(columnName), value);
    }

    public Optional<T> findOneByColumnName(String columnName, String value) {
        return iRepository.findOne(getSpecification(columnName, value));
    }

    public List<T> findAllByColumnName(String columnName, String value) {
        return iRepository.findAll(getSpecification(columnName, value));
    }

    public boolean existsByColumnName(String columnName, String value) {
        return iRepository.exists(getSpecification(columnName, value));
    }

}
