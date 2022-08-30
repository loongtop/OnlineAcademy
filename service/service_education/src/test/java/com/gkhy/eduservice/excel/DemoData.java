package com.gkhy.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //Set the excel header name
    @ExcelProperty(value = "Number",index = 0)
    private Integer number;
    @ExcelProperty(value = "Name",index = 1)
    private String name;
}
