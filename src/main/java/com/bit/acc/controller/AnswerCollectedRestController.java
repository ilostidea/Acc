package com.bit.acc.controller;

import com.bit.acc.model.AnswerCollected;
import com.bit.acc.service.intfs.AnswerCollectedService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * answerCollected 问答模块的回答收藏
 * @author ZL
 *
 */
@RestController
@RequestMapping("/answerCollected")
public class AnswerCollectedRestController {

    @Resource(name="answerCollectedService")
    private AnswerCollectedService answerCollectedService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get( 0 );
    		return new Response().failure( error.getDefaultMessage() );
        }
    	answerCollectedService.save(answerCollected);
        return new Response().success(answerCollected);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	answerCollectedService.save(answerCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("answerCollectedID") Long answerCollectedID) {
    	answerCollectedService.deleteById(answerCollectedID);
        return new Response().success();
    }

    @RequestMapping(value="/concern",method=RequestMethod.POST)
    public Response concern(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ObjectError error = errors.get(0);
            return new Response().failure(error.getDefaultMessage());
        }
        answerCollectedService.save(answerCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/unconcern",method=RequestMethod.POST)
    public Response unconcern(@Validated({First.class, Second.class, Third.class}) @RequestBody AnswerCollected answerCollected, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ObjectError error = errors.get(0);
            return new Response().failure(error.getDefaultMessage());
        }
    	answerCollectedService.delete(answerCollected);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部收藏的回答")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<AnswerCollected> listAnwerCollected = answerCollectedService.findAll();
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
    public Response queryByUser(@RequestParam("userID") Long userID) throws Exception {
    	List<AnswerCollected> listAnswerCollected = answerCollectedService.findByUser( userID );
        return new Response().success( listAnswerCollected );
    }
    
    @RequestMapping(value="/show/{answerCollectedID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long answerCollectedID){
    	AnswerCollected answerCollected = answerCollectedService.findById(answerCollectedID);
        return new Response().success(answerCollected);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("answerCollectedID") Long answerCollectedID){
    	AnswerCollected answerCollected = answerCollectedService.findById(answerCollectedID);
        return new Response().success( answerCollected );
    }
}
