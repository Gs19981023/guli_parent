package com.guo.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.commonutils.JwtUtils;
import com.guo.commonutils.MD5;
import com.guo.educenter.pojo.UcenterMember;
import com.guo.educenter.mapper.UcenterMemberMapper;
import com.guo.educenter.pojo.vo.RegisterVo;
import com.guo.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo.servicebase.exceptionhandler.DiyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author guoshuai
 * @since 2021-06-15
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        //手机号和密码做登录
        //1、获取手机号和密码
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();

        //2、如过手机号和密码，直接返回登录失败
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new DiyException(20001,"手机号和密码为空");
        }
        //判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);
        if (mobileMember == null){
            throw new DiyException(20001,"手机号不存在");
        }

        //判断密码是否相等
        //数据库密码进行加密，不能直接对比
        //MD5对密码进行加密，再与数据库进行比较
        String password1 = mobileMember.getPassword();
        if (!MD5.encrypt(password).equals(password1)){
            throw new DiyException(20001,"密码错误");
        }

        //判断用户是否被禁用
        if(mobileMember.getIsDisabled()){
            throw new DiyException(20001,"用户被禁用登录失败");
        }

        //登录成功
        //按照jwt生产token返回
        //根据查出来的对象值进行生成token
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return token;
    }


    //注册方法
    @Override
    public void register(RegisterVo registerVo){
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            throw new DiyException(20001,"注册失败");
        }
        //判断验证码
        String redisCode = "123456";
        System.out.println("redisCode:"+redisCode);
        System.out.println("code:"+code);
        if (!code.equals(redisCode)){
            throw new DiyException(20001,"注册失败");
        }

        //判断手机号在数据库中是否存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new DiyException(20001,"注册失败");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setIsDisabled(false);//用户不禁用
        ucenterMember.setAvatar("https://edu-longyang.oss-cn-beijing.aliyuncs.com/fa104ef58c4e5bc4270d911da1d1b34d.jpg");
        baseMapper.insert(ucenterMember);
    }
    @Override
    public UcenterMember getMemberByOpenId(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }


}
