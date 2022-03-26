package com.guo.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DiyException
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/21  14:30
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiyException extends RuntimeException {

    private Integer code;//状态码
    private String msg; //返回信息


}
