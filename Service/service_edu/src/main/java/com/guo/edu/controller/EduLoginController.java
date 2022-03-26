package com.guo.edu.controller;

import com.guo.commonutils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EduLogingController
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/23  16:28
 * @Version 1.0
 **/

@RestController
@RequestMapping("/edu/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","guoshuai");

    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","guoshuai").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
