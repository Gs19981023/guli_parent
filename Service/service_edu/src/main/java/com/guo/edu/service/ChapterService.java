package com.guo.edu.service;

import com.guo.edu.pojo.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.edu.pojo.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
