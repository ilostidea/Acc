/**
 * 2016-06-08
 */
package com.bit.common.exception;

import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Zhou Liang
 * 使用了ControllerAdvice后，RestControllerAdvice就不起作用
 */
//@ControllerAdvice
//@EnableWebMvc
//@ResponseBody
public class DefaultExceptionHandler {
	
	/**
     * 没有权限 异常
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
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }
    
}
