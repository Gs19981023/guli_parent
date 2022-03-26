package com.guo.edu.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName SubjectData
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/27  15:42
 * @Version 1.0
 **/
@Data
public class SubjectData {

    @ExcelProperty(index = 0)//对应excel表的第一列
    private String oneSubjectName;

    @ExcelProperty(index = 1)//对应excel表的第二列
    private String twoSubjectName;
}