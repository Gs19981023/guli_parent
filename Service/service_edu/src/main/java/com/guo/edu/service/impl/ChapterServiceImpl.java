package com.guo.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.edu.pojo.Chapter;
import com.guo.edu.mapper.ChapterMapper;
import com.guo.edu.pojo.Video;
import com.guo.edu.pojo.chapter.ChapterVo;
import com.guo.edu.pojo.chapter.VideoVo;
import com.guo.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.edu.service.VideoService;
import com.guo.servicebase.exceptionhandler.DiyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        QueryWrapper<Chapter> chapterQueryWrapper =new QueryWrapper();

        chapterQueryWrapper.eq("course_id",courseId);

        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        QueryWrapper<Video> videoQueryWrapper =new QueryWrapper();

        videoQueryWrapper.eq("course_id",courseId);

        List<Video> videoList = videoService.list(videoQueryWrapper);

        ArrayList<ChapterVo> finalList =new ArrayList<>();

        for(int i=0;i<chapterList.size();i++){
            Chapter chapter = chapterList.get(i);

            ChapterVo chapterVo = new ChapterVo();

            BeanUtils.copyProperties(chapter,chapterVo);

            finalList.add(chapterVo);

            ArrayList<VideoVo> finalVideoList = new ArrayList<>();

            for(int j=0;j<videoList.size();j++){
                Video video = videoList.get(j);


                if(video.getChapterId().equals(chapterVo.getId())){

                    VideoVo videoVo =new VideoVo();

                    BeanUtils.copyProperties(video,videoVo);
                    finalVideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(finalVideoList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {

        QueryWrapper<Video> wrapper =new QueryWrapper<>();

        wrapper.eq("chapter_id",chapterId);

        int count = videoService.count(wrapper);

        if(count>0){
            throw new DiyException(20001,"删除章节失败");

        }else{
            int result = baseMapper.deleteById(chapterId);

            return result>0;

        }
    }
}
