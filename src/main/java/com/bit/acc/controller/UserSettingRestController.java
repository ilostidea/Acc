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

import com.bit.acc.model.UserSetting;
import com.bit.acc.service.intfs.UserSettingService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

/**
 * userSetting 用户设置
 * @author ZL
 *
 */
@RestController
@RequestMapping("/userSetting")
public class UserSettingRestController {

    @Resource(name="userSettingService")
    private UserSettingService userSettingService;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody UserSetting userSetting, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	userSettingService.save(userSetting);
        return new Response().success(userSetting);
    }
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody UserSetting userSetting, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		return new Response().failure(error.getDefaultMessage());
        }
    	userSettingService.save(userSetting);
        return new Response().success();
    }
    
    @RequestMapping(value="/del",method=RequestMethod.POST)
    public Response del(@RequestParam("userSettingID") long userSettingID) {
    	userSettingService.deleteById( userSettingID );
        return new Response().success();
    }
    
    @RequestMapping(value="/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得全部设置")
    public Response queryAll() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<UserSetting> listUserSetting = userSettingService.findAll();
        return new Response().success( listUserSetting );
    }
    
    /**
     * 通过用户ID获得该用户的设置
     * @param userID
     * @return Response
     * @throws Exception
     */
    @RequestMapping(value="/queryBy",method=RequestMethod.GET)
    @ControllerLog(value = "通过用户ID获得该用户的设置")
    public Response queryByUser(@RequestParam("userID") long userID) throws Exception{
    	List<UserSetting> listUserSetting = userSettingService.queryByUser(userID);
        return new Response().success( listUserSetting );
    }
    
    @RequestMapping(value="/show/{userSettingID}",method=RequestMethod.GET)
    public Response show(@PathVariable long userSettingID){
    	UserSetting userSetting = userSettingService.findById(userSettingID);
        return new Response().success(userSetting);
    }
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("userSettingID") long userSettingID){
    	UserSetting userSetting = userSettingService.findById(userSettingID);
        return new Response().success( userSetting );
    }
}
