package com.guo.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.commonutils.R;
import com.guo.edu.mapper.TeacherMapper;
import com.guo.edu.pojo.Teacher;
import com.guo.edu.pojo.vo.TeacherQuery;
import com.guo.edu.service.TeacherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-17
 */
@RestController
@RequestMapping("/edu/teacher")
@Api(description = "讲师管理")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @ApiOperation(value = "展示全部教师列表")
    @GetMapping("findAll")
    public R findAll(){

//        QueryWrapper wrapper = new QueryWrapper();
        List<Teacher> list = teacherService.list(null);

        return R
                .ok()
                .data("items",list);
    }

    @DeleteMapping("delete/{id}")
    public R deleteById(@PathVariable("id") String id){
         boolean flag = teacherService.removeById(id);
         if(flag)
         {
             return R.ok();
         }
         else return R.error();

    }
    @ApiOperation(value = "分页展示老师页面")
    @GetMapping("pageTeacher/{current}/{limit}")
    //current :当前页
    //limit:每页的大小
    public R pageTeacher(@PathVariable("current") long current,
                         @PathVariable("limit") long limit){
        Page<Teacher> pageTeacher = new Page<>(current,limit);

        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();


        return R
                .ok()
                .data("total",total)
                .data("rows",records);
    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable("current") long current,
                                  @PathVariable("limit") long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<Teacher> pageTeacher = new Page<>(current,limit);


        QueryWrapper<Teacher> wrapper =new QueryWrapper<>();

        String name = teacherQuery.getName();
        String begin = teacherQuery.getBegin();
        Integer level = teacherQuery.getLevel();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        teacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();


        return R
                .ok()
                .data("total",total)
                .data("rows",records);
    }

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);

        if(save){
            return R.ok();
        }
        else return R.error();

    }

    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){

        Teacher teacher = teacherService.getById(id);

        return R
                .ok()
                .data("teacher",teacher);

    }
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){

        boolean res = teacherService.updateById(teacher);

        if(res){
            return R.ok();
        }
        else{
            return R.error();
        }

    }

}

