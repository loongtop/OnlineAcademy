package com.gkhy.servicebase.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.service.repository.IService;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class ControllerBase<T, E extends Number, Repository extends IService<T, E>>{

    private final Repository repository;
    @Autowired
    public ControllerBase(Repository repository) {
        this.repository = repository;
    }

    //Query all data in the lecturer table
    @GetMapping("all")
    public Result findAll() {
        //Call the method of service to query all operations
        List<T> teachers = repository.findAll();
        System.out.println("in the new controller!");
        return Result.success().data("items", teachers);
    }

    //Add the method of the lecturer interface
    @PostMapping("add")
    public Result add(@RequestBody Object o) {
        T t = (T) new Object();
        BeanUtils.copyProperties(o, t);
        T entity = repository.saveAndFlush(t);
        return Result.success().data("item", entity);
    }

    //Query by instructor id
    @GetMapping("get/{id}")
    public Result get(@PathVariable E id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }

    //Teacher modification function
    @PostMapping("update/{id}")
    public Result update(@PathVariable E id, @RequestBody EntityIsRemoved o) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            BeanUtils.copyProperties(o, t.get());
            T entity = repository.saveAndFlush(t.get());
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }

    //logically delete a teacher (IsDeleted = true)
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            EntityIsRemoved isEnable = new EntityIsRemoved();
            BeanUtils.copyProperties(isEnable, t);
            repository.saveAndFlush(t.get());
            return Result.success().data("message", "delete ok!");
        }
        return ItemFound.fail();
    }

    // delete a teacher from database
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            repository.deleteById(id);
            return Result.success().data("message", "delete ok!");
        }
        return ItemFound.fail();
    }
}
