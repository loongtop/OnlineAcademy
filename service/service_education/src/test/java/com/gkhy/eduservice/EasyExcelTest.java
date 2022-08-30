package com.gkhy.eduservice;

import com.alibaba.excel.EasyExcel;
import com.gkhy.eduservice.excel.DemoData;
import com.gkhy.eduservice.excel.ExcelListener;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    public static void main(String[] args) {
        // Implement the operation written in excel
        //1 Set the folder address and excel file name
        String fileName = System.currentTimeMillis() + "write" + ".xlsx";
        // 2 Call the method in easyexcel to implement the write operation
        // The write method has two parameters: the first parameter file path name, the second parameter entity class class
        EasyExcel.write(fileName, DemoData.class)
                .sheet("Student list")
                .doWrite(getData());

        // Realize excel reading operation
        EasyExcel.read(fileName, DemoData.class,new ExcelListener()).sheet("Student list").doRead();
    }

    // Generate data, create method returns list collection
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setNumber(i);
            data.setName("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
