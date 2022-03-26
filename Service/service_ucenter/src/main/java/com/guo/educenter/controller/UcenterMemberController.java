package com.guo.educenter.controller;


import com.guo.commonutils.JwtUtils;
import com.guo.commonutils.R;
import com.guo.educenter.pojo.UcenterMember;
import com.guo.educenter.pojo.vo.RegisterVo;
import com.guo.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Vector;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author guoshuai
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录方法
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember ucenterMember){

        String token = ucenterMemberService.login(ucenterMember);

        return R.ok().data("token",token);

    }

    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);

        return R.ok();
    }

    //根据token获取到用户的数据，为了前台页面的显示
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        UcenterMember member = ucenterMemberService.getById(memberId);

        return R.ok().data("member",member);



    }
}

