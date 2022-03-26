package com.guo.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CoursePublishVo
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/1  14:49
 * @Version 1.0
 **/
@Data
public class CoursePublishVo implements Serializable {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
