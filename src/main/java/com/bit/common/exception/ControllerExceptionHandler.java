/**
 * 2016-06-08
 */
package com.bit.common.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * @author Zhou Liang
 * 如果使用了ControllerAdvice，且其在 RestControllerAdvice 之前被注册，RestControllerAdvice就不起作用。
 * @see RestControllerExceptionHandler
 */
@ControllerAdvice( basePackageClasses = {com.bit.acc.controller.UserController.class} )
//@EnableWebMvc
//@ResponseBody
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	/**
     * SQL 异常
     * <p/>
     */
    @ExceptionHandler({SQLException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleSQLException(NativeWebRequest request, SQLException e) {
        logger.error( "SQLException:\r\n\t{}",  String.valueOf(e.getStackTrace()) );
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
    public ModelAndView handleConstraintViolationException(NativeWebRequest request, ConstraintViolationException e) {
        logger.error( "ConstraintViolationException:\r\n\t{}",  String.valueOf(e.getStackTrace()) );
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
    public ModelAndView handleUnauthorizedException(NativeWebRequest request, UnauthorizedException e) {
        logger.error( "UnauthorizedException:\r\n\t{}",  String.valueOf(e.getStackTrace()) );
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
    public ModelAndView handleUnauthenticatedException(NativeWebRequest request, AuthenticationException e) {
        logger.error( "AuthenticationException:\r\n\t{}",  String.valueOf(e.getStackTrace()) );
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", e);
        mv.setViewName("error");
        return mv;
    }
}
