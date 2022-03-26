package com.guo.edu.service.impl;

import com.alibaba.excel.EasyExcel;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.edu.listener.SubjectExcelListener;
import com.guo.edu.pojo.Subject;
import com.guo.edu.mapper.SubjectMapper;
import com.guo.edu.pojo.excel.SubjectData;
import com.guo.edu.pojo.subject.OneSubject;
import com.guo.edu.pojo.subject.TwoSubject;
import com.guo.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        //获取输入流
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getOneTwoSubject() {

        //①查询所有的一级分类

        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();

        wrapperOne.eq("parent_id","0");

        List<Subject> subjectOne = baseMapper.selectList(wrapperOne);

        //②查询所有的二级分类
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();

        wrapperTwo.ne("parent_id","0");

        List<Subject> subjectTwo = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终的封装数据
        List<OneSubject> finalSubject = new ArrayList<>();

        //③封装一级分类，我们想把
        for (Subject subject : subjectOne) {
            //把subject里面的值获取出来，放到OneSubject对象里


            OneSubject currentOne = new OneSubject();
//            currentOne.setId(subject.getId());
//            currentOne.setTitle(subject.getTitle());

            BeanUtils.copyProperties(subject,currentOne);//把subject的值get出来set到后面的对象中

            finalSubject.add(currentOne);

            //④封装二级分类
            //创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            for (Subject twoSubject : subjectTwo) {

                if(twoSubject.getParentId().equals(currentOne.getId())){

                    TwoSubject currentTwo = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,currentTwo);

                    twoFinalSubjectList.add(currentTwo);

                }
            }
            currentOne.setChildren(twoFinalSubjectList);
        }
        return finalSubject;
    }
}
