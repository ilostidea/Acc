/**
 * 2016-06-08
 */
package com.bit.common.exception;

import java.sql.SQLException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bit.common.model.Response;

/**
 * @author Zhou Liang
 *
 */
@RestControllerAdvice
@EnableWebMvc
@ResponseBody
public class RestExceptionHandler {
	
	/**
     * SQL异常
     * <p/>
     */
    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response processSQLException(NullPointerException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，出现了空指针异常！");
    }
	
	/**
     * SQL异常
     * <p/>
     */
    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response processSQLException(SQLException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，出现了SQL异常！");
    }

	/**
     * 保存时数据校验 异常 
     * <p/>
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response processConstraintViolationException(NativeWebRequest request, ConstraintViolationException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，数据校验未通过！");
    }
    
	/**
     * 没有权限 异常
     * <p/>
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response processUnauthenticatedException(UnauthorizedException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，您没有权限！");
    }
    
	/**
     * 没有找到数据
     * <p/>
     */
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response processEntityNotFoundException(EntityNotFoundException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，实体不存在！");
    }
    
}
