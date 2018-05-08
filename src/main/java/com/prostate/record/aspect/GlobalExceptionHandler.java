/**
 * 全局异常捕获
 */
package com.prostate.record.aspect;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Map<String,Object> resultMap;
    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        resultMap = new HashMap<>();
        log.error("我报错了：{}",exception.getLocalizedMessage());
        log.error("我报错了：{}",exception.getCause());
        log.error("我报错了：{}",exception.getSuppressed());
        log.error("我报错了：{}",exception.getMessage());
        log.error("我报错了：{}",exception.getStackTrace());

        resultMap.put("errorcode","50000");
        resultMap.put("errormsg",exception.getLocalizedMessage());
        return resultMap;
    }

    @ExceptionHandler(value = BindException.class)
    public Map<String,Object> bindExceptionHandler(HttpServletRequest request, BindException exception) throws Exception {
        resultMap = new HashMap<>();

        resultMap.put("code","40001");
        resultMap.put("msg","FAILED_PARAM");
        resultMap.put("result",exception.getFieldErrors());

        return resultMap;
    }


}
