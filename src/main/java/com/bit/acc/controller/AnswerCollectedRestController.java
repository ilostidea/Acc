package com.bit.acc.controller;

import java.util.List;

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

import com.bit.acc.model.AnswerCollected;
import com.bit.acc.model.QuestionCollected;
import com.bit.acc.service.intfs.IAnswerCollectedService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

/**
 * answerCollected 问答模块的回答收藏
 * @author ZL
 *
 */
@RestController
@RequestMapping("/answerCollected")
public class AnswerCollectedRestController {

    @Resource(name="answerCollectedService")
    private IAnswerCollectedService answerCollectedService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get( 0 );
    		return new Response().failure( error.getDefaultMessage() );
        }
    	answerCollectedService.persist(answerCollected);
        return new Response().success(answerCollected);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	answerCollectedService.merge(answerCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("answerCollectedID") long answerCollectedID) {
    	answerCollectedService.remove(answerCollectedID);
        return new Response().success();
    }
    
    @RequestMapping(value="/unconcern",method=RequestMethod.POST)
    public Response unconcern(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
    	answerCollectedService.remove(answerCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部收藏的回答")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<AnswerCollected> listAnwerCollected = answerCollectedService.queryAll();
        return new Response().success(listAnwerCollected);
    }
    
    /**
     * 通过用户ID获得该用户收藏的回答
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户收藏的回答")
    public Response queryByUser(@RequestParam("userID") long userID) throws Exception {
    	List<AnswerCollected> listAnswerCollected = answerCollectedService.queryByUser( userID );
        return new Response().success( listAnswerCollected );
    }
    
    @RequestMapping(value="/show/{answerCollectedID}",method=RequestMethod.GET)
    public Response show(@PathVariable long answerCollectedID){
    	AnswerCollected answerCollected = answerCollectedService.findById(answerCollectedID);
        return new Response().success(answerCollected);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("answerCollectedID") long answerCollectedID){
    	AnswerCollected answerCollected = answerCollectedService.findById(answerCollectedID);
        return new Response().success( answerCollected );
    }
}
