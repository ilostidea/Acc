package com.bit.acc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.UserService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.util.CipherUtil;
import com.bit.common.util.IConstants;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;

@RestController
@RequestMapping("/sysUser")
public class UserRestController {

    @Resource(name="sysUserService")
    private UserService userService;
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody SysUser sysUser, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		if(error instanceof FieldError) {
    			String field = ((FieldError) error).getField();
    			if ( "passwd".equals( field ) ){//注册新增时必须输入密码，编辑时可不输入密码;密码加密后的长度为32位，hibernate的校验在加密后校验，必须单独处理
    				if( sysUser.getPasswd() == null || !sysUser.getPasswd().matches( IConstants.REGEX_PASSWORD ) ) {
    					return new Response().failure("密码必须为6到16字符");
    				}
    			} else {
    	    		return new Response().failure(error.getDefaultMessage());
    			}
    		} else {
        		return new Response().failure(error.getDefaultMessage());
    		}
        }
    	
        String encrypted = CipherUtil.simpleHash("md5", sysUser.getPasswd(), null, 2, true);
        sysUser.setPasswd(encrypted);
        sysUser.setStatus(true);
        userService.save(sysUser);
        return new Response().success();
    }
    
    @RequestMapping(value="/admin/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody SysUser sysUser, BindingResult result) {
    	if(result.hasErrors()) {
    		List<ObjectError> errors = result.getAllErrors();
    		ObjectError error = errors.get(0);
    		if(error instanceof FieldError) {
    			String field = ((FieldError) error).getField();
    			if ( "passwd".equals( field ) ){//注册新增时必须输入密码，编辑时可不输入密码;密码加密后的长度为32位，hibernate的校验在加密后校验，必须单独处理
    				if( sysUser.getPasswd() == null || !sysUser.getPasswd().matches( IConstants.REGEX_PASSWORD ) ) {
    					return new Response().failure("密码必须为6到16字符");
    				}
    			} else {
    	    		return new Response().failure(error.getDefaultMessage());
    			}
    		} else {
        		return new Response().failure(error.getDefaultMessage());
    		}
        }
    	
        String encrypted = CipherUtil.simpleHash("md5", sysUser.getPasswd(), null, 2, true);
        sysUser.setPasswd(encrypted);
        userService.saveAndFlush(sysUser);
        return new Response().success();
    }
    
    /**
     * 获得用户列表信息，包含用户员工的信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/all",method=RequestMethod.GET)
    @ControllerLog(value = "获得用户列表")
    public Response getUserEmployee() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<SysUser> userList = userService.findAll();//throw new SQLException("What ?");
        return new Response().success(userList);
    }
    
    /**
     * 获得用户列表信息，不包含用户员工的信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ControllerLog(value = "获得用户列表")
    public Response getUsers() throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
        List<SysUser> userList = userService.findAll();
        return new Response().success(userList);
    }
    
    @RequestMapping(value="/show/{userid}",method=RequestMethod.GET)
    public Response show(@PathVariable long userid){
    	SysUser sysUser = userService.findById(userid);
        return new Response().success(sysUser);
    }
    
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("userid") long userid){
    	SysUser sysUser = userService.findById(userid);
        return new Response().success(sysUser);
    }
}
