package com.gkhy.servicebase.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.controller.helper.EntityIsEnabled;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.service.repository.IService;
import com.gkhy.servicebase.utils.ItemFound;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import lombok.SneakyThrows;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @Name: ControllerBase
 * @Description: ControllerBase for all the controller to extends
 * @Author: leo
 * @Created: 2022-07-06
 * @Updated: 2022-07-06
 * @Version: 1.0
 **/
@Validated
public abstract class ControllerBase<T, E extends Number, Repository extends IService<T, E>> {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Repository repository;

    @Autowired
    public ControllerBase(Repository repository) {
        this.repository = repository;
    }

    //Query all rows(data) from the table
    @GetMapping("/all")
    public List<T> findAll() {
        //Call the method of service to query all operations
        List<T> lists = repository.findAll();
        return lists;
    }

    //Add a record(row) to the table
    @PostMapping("/add")
    public T add(@RequestBody @NotEmpty JSONObject obj) {

        // Encrypt before storing for password
        if (obj.containsKey("password")) {
            String passwordMD5 = passwordEncoder.encode(String.valueOf(obj.get("password")));
            obj.replace("password", passwordMD5);
        }

        T entity = this.JSONObjectToT(obj);
        return repository.saveAndFlush(entity);
    }

    //Query by id
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable @Min(1) E id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isPresent()) return entity.get();

        return ItemFound.fail();
    }

    //update a record(row)
    @SneakyThrows
    @PutMapping("/update/{id}")
    public Object updateById(@PathVariable @Min(1) E id, @RequestBody @NotEmpty JSONObject obj) {
        Optional<T> tOptional = repository.findById(id);
        if (tOptional.isPresent()) {
            T entity = tOptional.get();

            PropertyUtilsBean bean = new PropertyUtilsBean();
            obj.remove("id");
            obj.remove("password");
            bean.copyProperties(entity, obj);

            return repository.saveAndFlush(entity);
        }
        return ItemFound.fail();
    }

    //update a record(row) by entity
    @PutMapping("/update")
    public Object update(@RequestBody @NotEmpty JSONObject obj) {

        if (!obj.containsKey("id")) {
            return Result.fail().codeAndMessage(StatusCode.PARAMS_FRONTEND_ERROR);
        }

        final E id = (E) obj.get("id");
        return updateById(id, obj);
    }

    //logically remove a record(row) (enabled = false)
    @DeleteMapping("/remove/{id}")
    public Object remove(@PathVariable @Min(1) E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            EntityIsEnabled isEnable = new EntityIsEnabled();
            BeanUtils.copyProperties(isEnable, t.get());
            repository.saveAndFlush(t.get());
            return Result.success().message(String.format("Remove item from visual list by id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //logically remove records(rows) (enabled = false)
    @DeleteMapping("/batchRemove")
    public Result removeByIds(@RequestParam("ids") @NotEmpty List<E> ids) {

        Set<E> unableRemoved = new HashSet<>();
        ids.forEach(id -> {
            Result t = (Result) remove(id);
            if (t.isFail()) unableRemoved.add(id);
        });

        if (unableRemoved.isEmpty()) {
            return Result.success();
        }
        return Result.success().data("Unable to removed list", unableRemoved);
    }

    //delete a record(row) from the table, Unable to restore
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable @Min(1) E id) {
        Optional<T> t = repository.findById(id);
        if (t.isPresent()) {
            repository.deleteById(id);
            return Result.success().message(String.format("Delete item from database by id %s ok!", id));
        }
        return ItemFound.fail();
    }

    //delete records(rows) from the table, Unable to restore
    @DeleteMapping("/batchDelete")
    public Result deleteByIds(@RequestParam("ids") @NotEmpty List<E> ids) {

        Set<E> unableDeleted = new HashSet<>();
        ids.forEach(id -> {
            if (!repository.existsById(id)) unableDeleted.add(id);
        });

        repository.deleteAllById(ids);

        if (unableDeleted.isEmpty()) return Result.success();

        return Result.success().data("Unable to delete list", unableDeleted);
    }

    //Method for querying lecturers by page
    //current page
    //the limit of the number of items
    @GetMapping("page/{current}/{limit}")
    public Result getByPage(@PathVariable @Min(1) int current, @PathVariable @Min(1) int limit) {

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
    protected T JSONObjectToT(@NotEmpty JSONObject jsonObject) {

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        final String className = parameterizedType.getActualTypeArguments()[0].getTypeName();

        Class clazz = Class.forName(className);
        T t = (T) clazz.getConstructor().newInstance();

        return (T) jsonObject.toJavaObject(t.getClass());
    }
}
