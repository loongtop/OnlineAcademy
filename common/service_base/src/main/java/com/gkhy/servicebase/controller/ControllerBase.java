package com.gkhy.servicebase.controller;

import com.gkhy.servicebase.controller.helper.EntityIsRemoved;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.service.repository.IService;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ControllerBase for all the controller to extends
 *
 * */

public abstract class ControllerBase<T, E extends Number, Repository extends IService<T, E>>
        implements IControllerBase<T, E> {

    private final Repository repository;
    @Autowired
    public ControllerBase(Repository repository) {
        this.repository = repository;
    }

    //Query all rows(data) from the table
    @GetMapping("/all")
    public Result findAll() {
        //Call the method of service to query all operations
        List<T> lists = repository.findAll();
        return Result.success().data("items", lists);
    }

    //Add a record(row) to the table
    @PostMapping("/add")
    public Result add(@RequestBody Object o) {
        T t = (T) new Object();
        BeanUtils.copyProperties(o, t);
        T entity = repository.saveAndFlush(t);
        return Result.success().data("item", entity);
    }

    //Save method
    @PostMapping("/save")
    public Result save(@RequestBody T t) {
        T entity = repository.saveAndFlush(t);
        return Result.success().data("item", entity);
    }

    //Query by id
    @GetMapping("/get/{id}")
    public Result getById(@PathVariable E id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }

    //update a record(row)
    @PostMapping("/update/{id}")
    public Result update(@PathVariable E id, @RequestBody EntityIsRemoved o) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            BeanUtils.copyProperties(o, t.get());
            T entity = repository.saveAndFlush(t.get());
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }

    //logically remove a record(row) (enabled = false)
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            EntityIsRemoved isEnable = new EntityIsRemoved();
            BeanUtils.copyProperties(isEnable, t.get());
            repository.saveAndFlush(t.get());
            return Result.success().data("message", String.format("Remove id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //logically remove records(rows) (enabled = false)
    @DeleteMapping("/batchRemove")
    public Result removeByIds(@RequestParam("ids") List<E> ids) {

        List<E> unableRemoved = new ArrayList<>();
        ids.forEach(id -> {
            Result t = remove(id);
           if (t.isFail()) unableRemoved.add(id);
        });

        if (unableRemoved.isEmpty()) {
            return Result.success();
        }
        return Result.success().data("unable to removed list", unableRemoved);
    }

    //delete a record(row) from the table, Unable to restore
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            repository.deleteById(id);
            return Result.success().data("message", String.format("Delete id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //delete records(rows) from the table, Unable to restore
    @DeleteMapping("/batchDelete")
    public Result deleteByIds(@RequestParam("ids") List<E> ids) {

        List<E> unableDeleted = new ArrayList<>();
        ids.forEach(id -> {
            if (!repository.existsById(id)) unableDeleted.add(id);
        });

        repository.deleteAllById(ids);

        if (unableDeleted.isEmpty()) return Result.success();

        return Result.success().data("unable to delete list", unableDeleted);
    }

    //Method for querying lecturers by page
    //current page
    //the limit of the number of items
    @GetMapping("page/{current}/{limit}")
    public Result getByPage(@PathVariable int current, @PathVariable int limit) {
        if (current <= 0 || limit <= 0) {
            return Result.fail().data("message", "Abnormal parameters!");
        }
        Pageable pageable = PageRequest.of(current-1, limit);
        Page<T> tPage = repository.findAll(pageable);
        long total = tPage.getNumberOfElements ();

        return Result.success().data("total",total).data("rows", tPage.getContent());
    }
}
