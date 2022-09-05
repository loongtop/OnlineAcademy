package com.gkhy.servicebase.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.servicebase.controller.helper.EntityIsEnabled;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.service.repository.IService;
import com.gkhy.servicebase.utils.ItemFound;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import lombok.SneakyThrows;

/**
 * ControllerBase for all the controller to extends
 */

public abstract class ControllerBase<T, E extends Number, Repository extends IService<T, E>>
        implements IControllerBase<E> {

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
    public Result add(@Valid @RequestBody JSONObject obj) {

        // Encrypt before storing for password
        if (obj.containsKey("password")) {
            String passwordMD5  = MD5.encrypt(String.valueOf(obj.get("password")));
            obj.replace("password", passwordMD5);
        }

        T entity = JSONObjectToT(obj);
        entity = repository.saveAndFlush(entity);
        return Result.success().data("item", entity);
    }

    //Query by id
    @GetMapping("/get/{id}")
    public Result getById(@Valid @PathVariable E id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }

    //update a record(row)
    @SneakyThrows
    @PutMapping("/update/{id}")
    public Result updateById(@Valid @PathVariable E id, @Valid @RequestBody JSONObject obj) {
        Optional<T> tOptional = repository.findById(id);
        if (tOptional.isPresent()) {
            T entity = tOptional.get();

            PropertyUtilsBean bean = new PropertyUtilsBean();
            obj.remove("id");
            obj.remove("password");
            bean.copyProperties(entity, obj);

            entity = repository.saveAndFlush(entity);
            return Result.success().data("item", entity);
        }
        return ItemFound.fail();
    }


    //update a record(row) by entity
    @PutMapping("/update")
    public Result update(@Valid @RequestBody JSONObject obj) {

        if (!obj.containsKey("id")) {
            return Result.fail().data("message", "There is no id in the object form frontend!");
        }

        final E id = (E) obj.get("id");
        return updateById(id, obj);
    }

    //logically remove a record(row) (enabled = false)
    @DeleteMapping("/remove/{id}")
    public Result remove(@Valid @PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            EntityIsEnabled isEnable = new EntityIsEnabled();
            BeanUtils.copyProperties(isEnable, t.get());
            repository.saveAndFlush(t.get());
            return Result.success().data("message", String.format("Remove id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //logically remove records(rows) (enabled = false)
    @DeleteMapping("/batchRemove")
    public Result removeByIds(@Valid @RequestParam("ids") List<E> ids) {

        Set<E> unableRemoved = new HashSet<>();
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
    public Result delete(@Valid @PathVariable E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            repository.deleteById(id);
            return Result.success().data("message", String.format("Delete id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //delete records(rows) from the table, Unable to restore
    @DeleteMapping("/batchDelete")
    public Result deleteByIds(@Valid @RequestParam("ids") List<E> ids) {

        Set<E> unableDeleted = new HashSet<>();
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
    public Result getByPage(@Valid @PathVariable int current, @Valid @PathVariable int limit) {
        if (current <= 0 || limit <= 0) {
            return Result.fail().data("message", "Abnormal parameters!");
        }
        Pageable pageable = PageRequest.of(current - 1, limit);
        Page<T> tPage = repository.findAll(pageable);
        long total = tPage.getNumberOfElements();

        return Result.success().data("total", total).data("rows", tPage.getContent());
    }


    /**
     * @Description: Use the reflection mechanism to obtain the type of T,
     * convert the data from the front end to type T, and store it in the database
     * @Param: jsonObject from frontend
     * @Return: T type
     * @Author: leo
     * @Date: 2022-09-01
     */
    @SneakyThrows
    protected T JSONObjectToT(JSONObject jsonObject) {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        final String className = parameterizedType.getActualTypeArguments()[0].getTypeName();

        Class clazz = Class.forName(className);
        T t = (T) clazz.getConstructor().newInstance();

        return (T) jsonObject.toJavaObject(t.getClass());
    }
}
