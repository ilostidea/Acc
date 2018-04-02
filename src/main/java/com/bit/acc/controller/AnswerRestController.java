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

import com.bit.acc.model.Answer;
import com.bit.acc.service.intfs.AnswerService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

/**
 * answer 问答模块的回答
 * @author ZL
 *
 */
@RestController
@RequestMapping("/answer")
public class AnswerRestController {

    @Resource(name="answerService")
    private AnswerService answerService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody Answer answer, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	answerService.save(answer);
    	Map<String, Long> mId = new HashMap<String, Long>( );
    	mId.put("id", answer.getId());
        return new Response().success(mId);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody Answer answer, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	answerService.save(answer);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("answerID") Long answerID) {
    	answerService.deleteById(answerID);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部回答")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<Answer> listAnswer = answerService.findAll();
        return new Response().success(listAnswer);
    }
    
    @RequestMapping(value="/admin/answer",method=RequestMethod.GET)
    @ControllerLog(value = "管理回答")
    public Response queryForAdmin(@RequestParam("userName") String userName, @RequestParam("answer") String answer, @RequestParam("status") Boolean status, @RequestParam("accused") Boolean accused) throws Exception{
        List<Answer> listAnswer = answerService.findForAdmin(userName, answer, status, accused);
        return new Response().success(listAnswer);
    }
    
    /**
     * 通过用户ID获得该用户的回答
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户的回答")
    public Response queryByUser(@RequestParam("userID") Long userID) throws Exception{
    	List<Answer> listQuestion = answerService.findByUser(userID);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过用户ID获得该用户收藏的回答
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryByCollectedUser",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户收藏的回答")
    public Response queryByCollectedUser(@RequestParam("userID") Long userID) throws Exception{
    	List<Answer> listQuestion = answerService.findByCollectedUser(userID);
        return new Response().success( listQuestion );
    }
    
    /**
     * 通过问题ID获得该问题的回答
     * @param questionID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过问题ID获得该问题的回答")
    public Response queryByQuestion(@RequestParam("questionID") Long questionID) throws Exception{
    	List<Answer> listAnswer = answerService.findByQuestion(questionID);
        return new Response().success(listAnswer);
    }
    
    @RequestMapping(value="/show/{answerID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long answerID){
    	Answer answer = answerService.findById(answerID);
        return new Response().success(answer);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("answerID") Long answerID){
    	Answer answer = answerService.findById(answerID);
        return new Response().success(answer);
    }
    
    /**
     * 点赞
     * @param answerID
     * @return
     */
    @RequestMapping(value="/approve",method=RequestMethod.POST)
    public Response approve(@RequestParam("answerID") Long answerID){
    	answerService.approve(answerID);
        return new Response().success();
    }

    /**
     * 踩
     * @param answerID
     * @return
     */
    @RequestMapping(value="/disapprove",method=RequestMethod.POST)
    public Response disapprove(@RequestParam("answerID") Long answerID){
    	answerService.disapprove(answerID);
        return new Response().success();
    }
    
}