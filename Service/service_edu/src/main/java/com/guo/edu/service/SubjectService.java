package com.guo.edu.service;

import com.guo.edu.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.edu.pojo.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-26
 */
public interface SubjectService extends IService<Subject> {

    void saveSubject(MultipartFile file,SubjectService subjectService);

    List<OneSubject> getOneTwoSubject();
}
