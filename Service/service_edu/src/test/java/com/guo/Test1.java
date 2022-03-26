package com.guo;

import com.guo.edu.pojo.Teacher;
import com.guo.edu.service.TeacherService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName Test1
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/24  15:10
 * @Version 1.0
 **/

@RestController
public class Test1 {

    @Autowired
    TeacherService teacherService;

    @Test
    public void test(){

        Teacher teacher = new Teacher();
        teacher.setName("郭帅1");
        teacher.setId("123456");
        teacher.setAvatar("https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        teacher.setCareer("高级讲师");
        teacher.setGmtCreate(new Date());
        teacher.setGmtModified(new Date());
        teacher.setIsDeleted(0);
        teacher.setSort(0);
        teacher.setIntro("0000");
        teacher.setLevel(0);


        teacherService.save(teacher);

    }
}
