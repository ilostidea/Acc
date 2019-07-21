package com.bit.acc.controller;

import com.bit.acc.model.Question;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.QuestionService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.util.IConstants;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * question 问答模块的问题
 * 
 * @author ZL
 *
 */
@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Resource(name="questionService")
    private QuestionService questionService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody Question question, BindingResult result) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
        question.setUser(user);
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	questionService.save(question);
    	Map<String, Long> mId = new HashMap<String, Long>( );
    	mId.put("id", question.getId());
        return new Response().success( mId );
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody Question question, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	questionService.save(question);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("questionID") Long questionID) {
    	questionService.deleteById( questionID );
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部问题")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<Question> listQuestion = questionService.findAll();
        return new Response().success( listQuestion );
    }
    
    @RequestMapping(value="/recent",method=RequestMethod.GET)
    @ControllerLog(value = "获得最近问题及问题概况")
    public Response queryRecent(/*@RequestParam(value = "userId", defaultValue = "0") Long userId, */@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size) throws Exception{
        if(page > 0)//前端传入page的值，从1开始，后端是从0开始计数
            page--;
        if(page < 0)
            page = 0;
        if(size <= 0)
            size = 15;
        Long userId = 0l;
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user != null)
            userId = user.getId();
        Pageable pageable = PageRequest.of(page, size);
        try {
            Map<String, Object> listQuestion = questionService.findRecent(userId, pageable);
            return new Response().success( listQuestion );
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * 通过用户ID获得该用户的提问
     * @param userId
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户的提问")
    public Response queryByUser(@RequestParam("userId") Long userId) throws Exception{
    	List<Question> listQuestion = questionService.findByUser(userId);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户回答的问题
     * @param userId
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByAnsweredUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户回答的问题")
    public Response queryByAnsweredUser(@RequestParam("userId") Long userId) throws Exception{
    	List<Question> listQuestion = questionService.findByAnsweredUser(userId);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户收藏的问题
     * //@param userId
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCollectedUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户收藏的问题")
    public Response queryByCollectedUser(@RequestParam("userId") Long userId) throws Exception{
    	List<Question> listQuestion = questionService.findByCollectedUser(userId);
        return new Response().success( listQuestion );
    }
    
    @RequestMapping(value="/show/{questionID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long questionID){
    	Question question = questionService.findById(questionID);
        return new Response().success(question);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("questionId") Long questionId/*,@RequestParam(value = "userId", defaultValue = "0") Long userId*/){
        Long userId = 0l;
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user != null)
            userId = user.getId();
        //Question question = questionService.getQuesstionAndAnswersById(questionId);
    	Question question = questionService.getQuesstionAndAnswersByIdAndUser( questionId, userId );
        return new Response().success( question );
    }
    
    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public Response profile( ){
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
    	Map<String, Long> questionProfile = questionService.getQuestionProfileById( user.getId() );
        return new Response().success( questionProfile );
    }

    /*====================================================================================
     * 以下是仅为管理员功能的接口
     */
    
    @RequestMapping(value="/admin/question",method=RequestMethod.GET)
    @ControllerLog(value = "管理问题")
    public Response queryForAdmin(@RequestParam("userName") String userName, @RequestParam("question") String question, @RequestParam("status") Boolean status, @RequestParam("accused") Boolean accused) throws Exception{
        List<Question> listQuestion = questionService.findByCondition( userName, question,  status, accused);
        return new Response().success( listQuestion );
    }
    
    @RequestMapping(value="/admin/detail",method=RequestMethod.GET)
    public Response detailForAdmin(@RequestParam("questionID") Long questionID){
    	//Question question = questionService.getQuesstionAndAnswersById(questionID);
    	Question question = questionService.getQuesstionAndAnswersByIdAndUserForAdmin( questionID );
        return new Response().success( question );
    }

    @RequestMapping(value="/admin/switchStatus",method=RequestMethod.POST)
    @ControllerLog(value = "启用/禁用问题")
    public Response switchStatus(@RequestParam(value="id") Long id,  @RequestParam("status") Boolean status ) throws Exception{
       questionService.switchStatus(id,  status );
        return new Response().success(  );
    }

}
