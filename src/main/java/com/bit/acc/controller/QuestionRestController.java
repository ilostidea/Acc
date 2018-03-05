package com.bit.acc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.acc.model.Question;
import com.bit.acc.service.intfs.IQuestionService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

/**
 * question 问答模块的问题
 * @author ZL
 *
 */
@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Resource(name="questionService")
    private IQuestionService questionService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody Question question, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	question.setIsAccused(false);
    	question.setStatus(true);
    	questionService.persist(question);
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
    	questionService.merge(question);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("questionID") long questionID) {
    	questionService.remove( questionID );
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部问题")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<Question> listQuestion = questionService.queryAll();
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户的提问
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户的提问")
    public Response queryByUser(@RequestParam("userID") long userID) throws Exception{
    	List<Question> listQuestion = questionService.queryByUser(userID);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户回答的问题
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByAnsweredUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户回答的问题")
    public Response queryByAnsweredUser(@RequestParam("userID") long userID) throws Exception{
    	List<Question> listQuestion = questionService.queryByAnsweredUser(userID);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户收藏的问题
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCollectedUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户收藏的问题")
    public Response queryByCollectedUser(@RequestParam("userID") long userID) throws Exception{
    	List<Question> listQuestion = questionService.queryByCollectedUser(userID);
        return new Response().success( listQuestion );
    }
    
    @RequestMapping(value="/show/{questionID}",method=RequestMethod.GET)
    public Response show(@PathVariable long questionID){
    	Question question = questionService.findById(questionID);
        return new Response().success(question);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("questionID") long questionID){
    	Question question = questionService.getQuesstionAndAnswersById(questionID);
        return new Response().success( question );
    }
    
    @RequestMapping(value="/profile",method=RequestMethod.GET)
    public Response profile(@RequestParam("userID") long userID){
    	Map questionProfile = questionService.getQuestionProfileById(userID);
        return new Response().success( questionProfile );
    }
    
}
