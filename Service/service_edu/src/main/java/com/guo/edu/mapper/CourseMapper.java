package com.guo.edu.mapper;

import com.guo.edu.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.edu.pojo.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo getCoursePublishVo(String courseId);

}
