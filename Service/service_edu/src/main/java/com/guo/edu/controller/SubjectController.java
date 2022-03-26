package com.guo.edu.controller;


import com.guo.commonutils.R;
import com.guo.edu.pojo.subject.OneSubject;
import com.guo.edu.service.SubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-26
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){

        subjectService.saveSubject(file,subjectService);

        return R.ok();
    }


    //课程分类列表（树形结构显示）
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        List<OneSubject> list =  subjectService.getOneTwoSubject();

        return R.ok().data("list",list);
    }


}

