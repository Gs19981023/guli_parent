package com.guo.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/26  20:40
 * @Version 1.0
 **/
public class ExcelListener extends AnalysisEventListener<DemoData> {
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("****" + demoData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }
}