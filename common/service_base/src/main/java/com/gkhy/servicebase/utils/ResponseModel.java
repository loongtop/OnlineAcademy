package com.gkhy.servicebase.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Name: ResponseModel
 * @Description: return modelAndView
 * @Author: leo
 * @Created: 2022-09-14
 * @Updated: 2022-09-14
 * @Version: 1.0
 **/
@Setter
@Getter
@AllArgsConstructor
public final class ResponseModel {

    private String url;
    private String dataName;
    private Object data;

    public ResponseModel(String url) {
        this.url = url;
    }

    public ResponseModel(String url, Object data) {
        this.url = url;
        this.dataName = "data";
        this.data = data;
    }

    public static ModelAndView of(String url) {
        ResponseModel r = new ResponseModel(url);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(r.getUrl());

        return modelAndView;
    }

    public static ModelAndView of(String url, Object data) {
        ResponseModel r = new ResponseModel(url, data);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(r.getUrl());
        modelAndView.addObject(r.getDataName(), r.getData());

        return modelAndView;
    }

    public static ModelAndView of(String url, String name, Object data) {
        ResponseModel r = new ResponseModel(url, name, data);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(r.getUrl());
        modelAndView.addObject(name, r.getData());

        return modelAndView;
    }
}
