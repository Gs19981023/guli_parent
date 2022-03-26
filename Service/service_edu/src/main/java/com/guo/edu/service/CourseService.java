package com.guo.edu.service;

import com.guo.edu.pojo.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.edu.pojo.vo.CourseInfoVo;
import com.guo.edu.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String courseId);
}
