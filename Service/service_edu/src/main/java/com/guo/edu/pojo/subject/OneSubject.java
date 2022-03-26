package com.guo.edu.pojo.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OneSubject
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/29  13:32
 * @Version 1.0
 **/
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<>();

}