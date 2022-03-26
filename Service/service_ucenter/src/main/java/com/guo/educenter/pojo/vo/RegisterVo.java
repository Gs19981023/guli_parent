package com.guo.educenter.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RegisterVo
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/6/15  15:58
 * @Version 1.0
 **/
@Data
public class RegisterVo implements Serializable {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}