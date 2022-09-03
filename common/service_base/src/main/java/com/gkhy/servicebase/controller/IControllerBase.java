package com.gkhy.servicebase.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IControllerBase<E> {
    Result findAll();

    Result add(@Valid @RequestBody JSONObject obj);

    Result getById(@PathVariable E id);

    Result updateById(@Valid @PathVariable E id, @Valid @RequestBody JSONObject obj);

    Result update(@RequestBody JSONObject o);

    Result remove(@PathVariable E id);

    Result removeByIds(@PathVariable List<E> idList);

    Result delete(@PathVariable E id);

    Result deleteByIds(@PathVariable List<E> idList);

    Result getByPage(@PathVariable int current, @PathVariable int limit);
}
