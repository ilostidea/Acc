package com.bit.acc.controller;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.UserService;
import com.bit.common.log.ControllerLog;
import com.bit.common.model.Response;
import com.bit.common.util.CipherUtil;
import com.bit.common.util.IConstants;
import com.bit.common.validation.First;
import com.bit.common.validation.Second;
import com.bit.common.validation.Third;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class UserRestController {

    @Resource(name="sysUserService")
    private UserService userService;

    private Response validate(SysUser sysUser, BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();
        ObjectError error = errors.get(0);
        if(error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            if ( "passwd".equals( field ) ){//注册新增时必须输入密码，编辑时可不输入密码;密码加密后的长度为32位，hibernate的校验在加密后校验，必须单独处理
                if( sysUser.getPasswd() == null || !sysUser.getPasswd().matches( IConstants.REGEX_PASSWORD ) ) {
                    return new Response().failure("密码必须为6到16字符");
                } else
                    return null;
            } else {
                return new Response().failure(error.getDefaultMessage());
            }
        } else {
            return new Response().failure(error.getDefaultMessage());
        }
    }

    @ControllerLog(value = "新增用户")
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public Response add(@Validated({First.class, Second.class, Third.class}) @RequestBody SysUser sysUser, BindingResult result) {
    	if(result.hasErrors()) {
            Response response = validate(sysUser, result);
            if(response  != null)
                return response;
        }
        String encrypted = CipherUtil.simpleHash("md5", sysUser.getPasswd(), null, 2, true);
        sysUser.setPasswd(encrypted);
        sysUser.setStatus(true);
        userService.save(sysUser);
        return new Response().success();
    }

    @ControllerLog(value = "修改用户信息")
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Response update(@Validated({First.class, Second.class, Third.class}) @RequestBody SysUser sysUser, BindingResult result) {
    	if(result.hasErrors()) {
            Response response = validate(sysUser, result);
            if(response  != null)
                return response;
        }
        String encrypted = CipherUtil.simpleHash("md5", sysUser.getPasswd(), null, 2, true);
        sysUser.setPasswd(encrypted);
        userService.saveAndFlush(sysUser);
        return new Response().success();
    }
    
    @RequestMapping(value="/show/{userid}",method=RequestMethod.GET)
    public Response show(@PathVariable Long userid){
    	SysUser sysUser = userService.findById(userid);
        return new Response().success(sysUser);
    }
    
    
    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public Response detail(@RequestParam("userid") Long userid){
    	SysUser sysUser = userService.findById(userid);
        return new Response().success(sysUser);
    }

/**
 * ==================For Admin User===========================================================
 */
    
    /**
     * 获得用户列表信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ControllerLog(value = "获得用户列表")
    public Response getUsers() throws Exception{
    	//测试异常处理        if(true) throw new NullPointerException("空指针异常");
        List<SysUser> userList = userService.findAll();
        return new Response().success(userList);
    }
    
    /**
     * 获得用户列表信息
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
    @RequestMapping(value="/admin/stat",method=RequestMethod.GET)
    @ControllerLog(value = "获得用户列表")
    public Response stat(@RequestParam(value="from", required=false) Date from, @RequestParam(value="to", required=false) Date to) throws Exception{
    	//测试异常处理 if(true) throw new SQLException("SQL异常");
    	if(to == null)
    		to = new Date();
    	if(from == null) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(to);
    		calendar.add(Calendar.DATE, -30);
    		from = calendar.getTime();
    	}
    	Long[][] userList = userService.getNewUsersAndTotalUsersByDate(from, to);
        return new Response().success(userList);
    }
}
