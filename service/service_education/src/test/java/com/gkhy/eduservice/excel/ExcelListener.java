package com.gkhy.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DemoData> {
    //Read excel content line by line
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }

    //read header content
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("Head："+headMap);
    }
    //After reading is complete
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) { }
}
