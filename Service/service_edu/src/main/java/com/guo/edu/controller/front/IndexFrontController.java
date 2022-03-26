package com.guo.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.commonutils.R;
import com.guo.edu.pojo.Course;
import com.guo.edu.pojo.Teacher;
import com.guo.edu.service.CourseService;
import com.guo.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName IndexFrontController
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/7  12:36
 * @Version 1.0
 **/

@RestController
@RequestMapping("/edu/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;



    @GetMapping("getHotInfo")
    public R getHotInfo(){

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<Course> courseList =courseService.list(courseQueryWrapper);

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);


        return R.ok().data("courseList",courseList).data("teacherList",teacherList);


    }


}
