package com.bit.acc.controller;

import com.bit.acc.model.QuestionCollected;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.QuestionCollectedService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.util.IConstants;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * questionCollected 问答模块的问题收藏
 * @author ZL
 *
 */
@RestController
@RequestMapping("/questionCollected")
public class QuestionCollectedRestController {

    @Resource(name="questionCollectedService")
    private QuestionCollectedService questionCollectedService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody QuestionCollected questionCollected, BindingResult result) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
        questionCollected.setUser(user);
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	questionCollectedService.save(questionCollected);
        return new Response().success(questionCollected);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody QuestionCollected questionCollected, BindingResult result) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
        questionCollected.setUser(user);
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	questionCollectedService.save(questionCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("questionCollectedID") Long questionCollectedID) {
    	questionCollectedService.deleteById(questionCollectedID);
        return new Response().success();
    }


    @RequestMapping(value="/concern",method=RequestMethod.POST)
    public Response concern(@Validated({First.class, Second.class, Third.class}) @RequestBody QuestionCollected questionCollected, BindingResult result) {
        return add(questionCollected, result);
    }

    @RequestMapping(value="/unconcern",method=RequestMethod.POST)
    public Response unconcern(@Validated({First.class, Second.class, Third.class}) @RequestBody QuestionCollected questionCollected, BindingResult result) {

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated())
            throw new AuthenticationException("您未登录，获取不到用户信息！");

        SysUser user = (SysUser) subject.getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);

        questionCollectedService.deleteByUserId(questionCollected, user.getId());
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部收藏的问题")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<QuestionCollected> listQuestionCollected = questionCollectedService.findAll();
        return new Response().success( listQuestionCollected );
    }
    
    /**
     * 通过用户ID获得该用户收藏的问题
     * //@param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户收藏的问题")
    public Response queryByUser(/*@RequestParam("userID") Long userID*/) throws Exception{
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
    	List<QuestionCollected> listQuestionCollected = questionCollectedService.findByUser(user.getId());
        return new Response().success( listQuestionCollected );
    }
    
    @RequestMapping(value="/show/{questionCollectedID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long questionCollectedID){
    	QuestionCollected questionCollected = questionCollectedService.findById(questionCollectedID);
        return new Response().success(questionCollected);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("questionCollectedID") Long questionCollectedID){
    	QuestionCollected questionCollected = questionCollectedService.findById(questionCollectedID);
        return new Response().success( questionCollected );
    }
}
