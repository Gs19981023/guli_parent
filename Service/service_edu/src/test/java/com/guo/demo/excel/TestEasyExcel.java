package com.guo.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestEasyExcel
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/26  19:19
 * @Version 1.0
 **/
public class TestEasyExcel {


    public static void main(String[] args) {


        String filename ="C:\\Users\\GuoSheng\\Desktop\\write.xlsx";
//
//        EasyExcel
//                .write(filename,DemoData.class)
//                .sheet("学生列表").doWrite(getData());
        ExcelReaderBuilder read = EasyExcel.read(filename,DemoData.class,new ExcelListener());

        read.sheet().doRead();

    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i =0;i<10;i++){
            DemoData demoData =new DemoData();

            demoData.setSname("郭帅"+i);
            demoData.setSno(1120201310+i);

            list.add(demoData);
        }

        return list;
    }
}
