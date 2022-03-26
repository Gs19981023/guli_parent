package com.guo.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.edu.pojo.Subject;
import com.guo.edu.pojo.excel.SubjectData;
import com.guo.edu.service.SubjectService;
import com.guo.servicebase.exceptionhandler.DiyException;

import java.util.Map;

/**
 * @ClassName SubjectExcelListener
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/27  15:52
 * @Version 1.0
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new DiyException(20001, "文件数据为空");
        }
        //判断一级分类
        Subject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (oneSubject == null) {//没有相同的一级分类，进行添加
            oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }

        //判断二级分类
        String pid = oneSubject.getId();
        Subject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoSubject == null) {//没有相同的一级分类，进行添加
            twoSubject = new Subject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }
    }

    //判断一级分类，不能重复添加
    private Subject existOneSubject(SubjectService SubjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        Subject one = SubjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类，不能重复添加
    private Subject existTwoSubject(SubjectService SubjectService, String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        Subject one = SubjectService.getOne(wrapper);
        return one;
    }


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}