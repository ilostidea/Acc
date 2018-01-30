/**
 * 2016-06-08
 */
package com.bit.common.exception;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Zhou Liang
 * 使用了ControllerAdvice后，RestControllerAdvice就不起作用，spring MVC配置文件中的bean也不起作用。bean配置如下：
 * <bean id="exceptionHandler" class="com.bit.common.exception.AccHandlerExceptionResolver"/>
 */
//@ControllerAdvice
//@EnableWebMvc
//@ResponseBody
public class DefaultExceptionHandler {
	
	/**
     * SQL 异常
     * <p/>
     */
    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processSQLException(NativeWebRequest request, SQLException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }
    

	/**
     * 数据校验 异常
     * <p/>
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processConstraintViolationException(NativeWebRequest request, ConstraintViolationException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }

	/**
     * 没有权限 异常
     * <p/>
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthorizedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }

	/**
     * 没有权限 异常
     * <p/>
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, AuthenticationException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }
}
