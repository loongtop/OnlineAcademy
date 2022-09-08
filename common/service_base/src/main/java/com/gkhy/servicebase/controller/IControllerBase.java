package com.gkhy.servicebase.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.controller.helper.EntityIsEnabled;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.utils.ItemFound;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IControllerBase<T, E> {
    //Query all rows(data) from the table
    @GetMapping("/all")
    List<T> findAll();

    //Add a record(row) to the table
    @PostMapping("/add")
    T add(@RequestBody @NotEmpty JSONObject obj);

    //Query by id
    @GetMapping("/get/{id}")
    Object getById(@PathVariable @Min(1) E id);

    //update a record(row)
    @PutMapping("/update/{id}")
    Object updateById(@PathVariable @Min(1) E id, @RequestBody @NotEmpty JSONObject obj);

    //update a record(row) by entity
    @PutMapping("/update")
    Object update(@RequestBody @NotEmpty JSONObject obj);

    //logically remove a record(row) (enabled = false)
    @DeleteMapping("/remove/{id}")
    Object remove(@PathVariable @Min(1) E id);

    //logically remove records(rows) (enabled = false)
    @DeleteMapping("/batchRemove")
    Result removeByIds(@RequestParam("ids") @NotEmpty List<E> ids);

    //delete a record(row) from the table, Unable to restore
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable @Min(1) E id);

    //delete records(rows) from the table, Unable to restore
    @DeleteMapping("/batchDelete")
    Result deleteByIds(@RequestParam("ids") @NotEmpty List<E> ids);

    @GetMapping("page/{current}/{limit}")
    Result getByPage(@PathVariable @Min(1) int current, @PathVariable @Min(1)int limit);
}
