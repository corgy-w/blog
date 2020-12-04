package cn.corgy;

import cn.corgy.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerResolve {

    @ExceptionHandler(value = ParamException.class)//想要处理的异常
    @ResponseBody
    public Map<String, Object> exceptionHandler(ParamException paramException) {
        Map<String, Object> map = new HashMap<>();
        log.error("{}访问错误", paramException.getMsg());
        map.put("code", paramException.getCode());
        map.put("msg", paramException.getMsg());
        return map;
    }

    /**
     * 处理查入数据库时的错误类型
     *
     * @return 错误map
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)//想要处理的异常
    @ResponseBody
    public Map<String, Object> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        Map<String, Object> map = new HashMap<>();
        log.error("{}访问错误", e.getHttpInputMessage());
        map.put("code", 500);
        map.put("msg", "参数类型不正确");
        return map;
    }

}
