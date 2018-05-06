/**
 * 2016-06-08
 */
package com.bit.acc.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import com.google.common.base.Throwables;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bit.common.model.Response;

/**
 * @author Zhou Liang
 *
 */
@RestControllerAdvice( annotations = RestController.class)
//@EnableWebMvc
//@ResponseBody
public class RestControllerExceptionHandler {

    /**
     * 参数校验不通过
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        Response result = new Response().failure(tips);
        return result;
    }
	/**
     * 请求的资源不存在
     * <p/>
     */
    @ExceptionHandler({FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response processNotFoundException(FileNotFoundException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，请求的界面不存在！");
    }
	
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
    @ResponseBody
    public Response processSQLException(SQLException e) {
//        Throwables.getRootCause(e).getMessage();
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
     * 数据约束校验 异常 
     * <p/>
     */
    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response processConstraintViolationException(NativeWebRequest request, org.hibernate.exception.ConstraintViolationException e) {
        return new Response().failure(e.getMessage() != null? e.getMessage() : "对不起，数据已被引用！");
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
