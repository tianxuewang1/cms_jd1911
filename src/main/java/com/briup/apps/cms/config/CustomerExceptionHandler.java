package com.briup.apps.cms.config;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

/**
 * @program: app01
 * @description: 统一异常处理类
 * @author: charles
 * @create: 2019-03-13 21:03
 **/
@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(value =  Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
    public <E> Message handler(Exception exception){
        exception.printStackTrace();
        if(exception instanceof CustomerException) {
        	return MessageUtil.error(exception.getMessage());
        }
        if(exception instanceof DataIntegrityViolationException) {
        	return MessageUtil.error("此数据和其它数据有关联,无法删除");
        }
        if(exception instanceof ConstraintViolationException) {
        	String message = exception.getMessage();
        	String[] split = message.split("[.]");
        	
        	return MessageUtil.error(split[1]);
        }
        return MessageUtil.error("后台异常");
    }
}
