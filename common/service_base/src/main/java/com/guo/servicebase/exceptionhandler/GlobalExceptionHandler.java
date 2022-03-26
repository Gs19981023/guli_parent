package com.guo.servicebase.exceptionhandler;





import com.guo.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author GuoSheng
 * @Date 2021/5/21  14:06
 * @Version 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler  {

    public R error(Exception e ){
        e.printStackTrace();
        return R
                .error()
                .message("执行了全局异常处理");
    }
}
