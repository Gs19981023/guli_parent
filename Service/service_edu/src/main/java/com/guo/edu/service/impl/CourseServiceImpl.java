package com.guo.edu.service.impl;

import com.guo.edu.pojo.Course;
import com.guo.edu.mapper.CourseMapper;
import com.guo.edu.pojo.CourseDescription;
import com.guo.edu.pojo.vo.CourseInfoVo;
import com.guo.edu.pojo.vo.CoursePublishVo;
import com.guo.edu.service.ChapterService;
import com.guo.edu.service.CourseDescriptionService;
import com.guo.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.edu.service.VideoService;
import com.guo.servicebase.exceptionhandler.DiyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;
    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;


    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //1.向课程表添加课程基本信息
        Course course =new Course();
        BeanUtils.copyProperties(courseInfoVo,course);
        int result = baseMapper.insert(course);

        if(result ==0){
            throw new DiyException(20001,"添加课程信息失败");
        }


        String cid = course.getId();
        //2.向课程简介表添加课程简介

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);

        return cid;
    }


    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        Course course = baseMapper.selectById(courseId);


        CourseInfoVo courseInfoVo =new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        CourseDescription courseDescription = courseDescriptionService.getById(courseId);

        courseInfoVo.setDescription(courseDescription.getDescription());


        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        Course course = new Course();

        BeanUtils.copyProperties(courseInfoVo,course);

        int update = baseMapper.updateById(course);

        if(update==0){
            throw new DiyException(20001,"修改课程信息失败");
        }
        //修改描述表
        CourseDescription courseDescription =new CourseDescription();

        courseDescription.setDescription(courseInfoVo.getDescription());

        courseDescriptionService.updateById(courseDescription);

    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {

        CoursePublishVo coursePublishVo = baseMapper.getCoursePublishVo(id);

        return coursePublishVo;
    }

    @Override
    public void removeCourse(String courseId) {
        chapterService.removeById(courseId);

        videoService.removeById(courseId);

        courseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);

        if(result==0){
            throw new DiyException(20001,"删除失败");
        }
    }
}
