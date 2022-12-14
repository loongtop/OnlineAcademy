package com.gkhy.servicebase.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.controller.helper.EntityIsEnabled;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.service.repository.IService;
import com.gkhy.servicebase.utils.ItemFound;
import com.gkhy.servicebase.utils.ResponseModel;
import com.gkhy.servicebase.utils.hibernate.Criteria;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import lombok.SneakyThrows;
import org.springframework.web.servlet.ModelAndView;

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

    private String className;
    private String name;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final Repository repository;

    @Autowired
    public ControllerBase(Repository repository) {
        this.repository = repository;
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        className = parameterizedType.getActualTypeArguments()[0].getTypeName();
        name = className.split("\\.")[className.split("\\.").length - 1].toLowerCase(Locale.ROOT);
    }

    //Query all rows(data) from the table
    @GetMapping("/list")
    public Object findAll() {
        //Call the method of service to query all operations
        List<T> lists = repository.findAll();
        return ResponseModel.of("test", lists);
//        return ResponseModel.of(name, lists);
    }

    //Add a record(row) to the table
//    @PostMapping("/add")
//    public Object add(@RequestBody @NotEmpty JSONObject obj) {
//
//        // Encrypt before storing for password
//        if (obj.containsKey("password")) {
//            String passwordMD5 = passwordEncoder.encode(String.valueOf(obj.get("password")));
//            obj.replace("password", passwordMD5);
//        }
//
//        T entity = this.JSONObjectToT(obj);
//        return new ModelAndView("redirect:all");
//    }

    //Query by id
    @GetMapping("/get/{id}")
    public Object getById(@PathVariable @Min(1) E id) {
        Optional<T> entity = repository.findById(id);
        if (entity.isEmpty()) return ItemFound.fail();

        return entity.get();
    }

    //update a record(row)
    @SneakyThrows
    @PutMapping("/update/{id}")
    public Object updateById(@PathVariable @Min(1) E id, @RequestBody @NotEmpty JSONObject obj) {
        Optional<T> item = repository.findById(id);
        if (item.isEmpty()) return ItemFound.fail();

        T entity = item.get();
        PropertyUtilsBean bean = new PropertyUtilsBean();
        obj.remove("id");
        obj.remove("password");
        bean.copyProperties(entity, obj);

        repository.saveAndFlush(entity);
        return new ModelAndView("redirect:all");
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
        if (t.isEmpty()) return ItemFound.fail();

        EntityIsEnabled isEnable = new EntityIsEnabled();
        BeanUtils.copyProperties(isEnable, t.get());
        repository.saveAndFlush(t.get());
//        return Result.success().message(String.format("Remove item from visual list by id %s ok!", id));
        return new ModelAndView("redirect:all");
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
    public Object delete(@PathVariable @Min(1) E id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ModelAndView("redirect:all");
//            return Result.success().message(String.format("Delete item from database by id %s ok!", id));
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
    protected T JSONObjectToT(JSONObject jsonObject) {

        Class clazz = Class.forName(this.className);
        T t = (T) clazz.getConstructor().newInstance();

        return (T) jsonObject.toJavaObject(t.getClass());
    }
}
