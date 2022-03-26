package com.guo.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.commonutils.R;
import com.guo.edu.pojo.Course;
import com.guo.edu.pojo.Teacher;
import com.guo.edu.pojo.vo.CourseInfoVo;
import com.guo.edu.pojo.vo.CoursePublishVo;
import com.guo.edu.pojo.vo.TeacherQuery;
import com.guo.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){



        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id){

        CourseInfoVo courseInfoVo =courseService.getCourseInfo(id);

        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCourseInfo(courseInfoVo);

    return R.ok();
    }
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        Course course =new Course();

        course.setId(id);

        course.setStatus("Normal");

        courseService.updateById(course);

        return R.ok();
    }

    @GetMapping("getListCourse")

    public R getListCourse(){

        List<Course> list = courseService.list(null);

        return R.ok().data("list",list);
    }

    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){

        courseService.removeCourse(courseId);

        return R.ok();
    }





}

