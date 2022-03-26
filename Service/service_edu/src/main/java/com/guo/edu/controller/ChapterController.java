package com.guo.edu.controller;


import com.guo.commonutils.R;
import com.guo.edu.pojo.Chapter;
import com.guo.edu.pojo.chapter.ChapterVo;
import com.guo.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;


    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){

        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("allChapterVideo",list);

    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter Chapter) {
        chapterService.save(Chapter);
        return R.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {

        Chapter chapter = chapterService.getById(chapterId);

        return R.ok().data("chapter", chapter);
    }


    @PostMapping("updateChapterVideo")
    public R  updateChapterVideo(@RequestBody Chapter chapter){

        chapterService.updateById(chapter);

        return R.ok();
    }

    //删除课程章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapter(chapterId);
        if (result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

