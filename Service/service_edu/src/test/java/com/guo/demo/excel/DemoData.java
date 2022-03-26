package com.guo.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sun.org.glassfish.gmbal.AMXMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DemoData
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/26  19:17
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {

    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
