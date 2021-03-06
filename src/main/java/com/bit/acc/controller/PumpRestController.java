package com.bit.acc.controller;

import java.util.List;

import javax.annotation.Resource;

import com.bit.acc.model.SysUser;
import com.bit.common.util.IConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.acc.model.Pump;
import com.bit.acc.service.intfs.PumpService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

/**
 * pump 问答模块的追问
 * @author ZL
 *
 */
@RestController
@RequestMapping("/pump")
public class PumpRestController {

    @Resource(name="pumpService")
    private PumpService pumpService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody Pump pump, BindingResult result) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(IConstants.CURRENT_USER_SESSION_KEY);
        if(user == null)
            throw new AuthenticationException("您未登录，获取不到用户信息！");
        pump.setUser( user );
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	pumpService.save(pump);
        return new Response().success(pump);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody Pump pump, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	pumpService.save(pump);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("pumpID") Long pumpID) {
    	pumpService.deleteById(pumpID);
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部追问")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<Pump> listPump = pumpService.findAll();
        return new Response().success(listPump);
    }
    
    /**
     * 通过问题答案ID获得该问题的追问
     * @param answerID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过问题ID获得该问题的追问")
    public Response queryByAnswer(@RequestParam("answerID") Long answerID) throws Exception{
    	List<Pump> listPump = pumpService.findByAnswer(answerID);
        return new Response().success(listPump);
    }
    
    @RequestMapping(value="/show/{pumpID}",method=RequestMethod.GET)
    public Response show(@PathVariable Long pumpID){
    	Pump pump = pumpService.findById(pumpID);
        return new Response().success(pump);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("pumpID") Long pumpID){
    	Pump pump = pumpService.findById(pumpID);
        return new Response().success(pump);
    }

    /*====================================================================================
     * 以下是仅为管理员功能的接口
     */
    @RequestMapping(value="/admin/switchStatus",method=RequestMethod.POST)
    @ControllerLog(value = "启用/禁用回答")
    public Response switchStatus(@RequestParam("id") Long id,  @RequestParam("status") Boolean status ) throws Exception{
    	pumpService.switchStatus(id,  status );
        return new Response().success(  );
    }
}
