package com.guo.educenter.service;

import com.guo.educenter.pojo.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.educenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author guoshuai
 * @since 2021-06-15
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    UcenterMember getMemberByOpenId(String openId);
}
