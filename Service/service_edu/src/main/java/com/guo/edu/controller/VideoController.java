package com.guo.edu.controller;


import com.guo.commonutils.R;
import com.guo.edu.pojo.Video;
import com.guo.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-05-29
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video){
        videoService.save(video);

        return R.ok();
    }

    @DeleteMapping("{videoId}")
    public R deleteVideoById(@PathVariable String videoId){

        videoService.removeById(videoId);

        return R.ok();

    }

    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody Video video){
        videoService.updateById(video);

        return R.ok();
    }

    @GetMapping("getVideoById/{id}")
    public R getVideoById(@PathVariable String id){

        Video video = videoService.getById(id);

        return R.ok().data("video",video);

    }

}

