package com.bit.acc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.UserService;
import com.bit.common.util.CipherUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
    @Resource(name="sysUserService")
    private UserService userService;
    
    @RequestMapping("/hello")
    public String hello() {
        SecurityUtils.getSubject().checkRole("admin");
        return "user/admin/list";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
		return "redirect:/jsp/signin.jsp";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
    	String successUrl = "/index";// 登录成功Shiro进行过滤，跳转到shiro的successUrl，并不会跳转到此URL
    	Subject user = SecurityUtils.getSubject();
    	if ( user.isAuthenticated() )
    		return successUrl;//如果已经登录过，再次登录的时候，不需要进行验证，直接跳转到successUrl
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		try {
			user.login(token);
			token.clear();
		} catch (AuthenticationException e) {
			log.error("登录失败错误信息:" + e);//throw new AuthenticationException(e);
			return "redirect:/user/login";
		}
		return successUrl;
    }

    @RequestMapping("/logout")
    public String logout() {
    	Subject user = SecurityUtils.getSubject();
    	if (user.isAuthenticated()) {
    		user.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			log.error("{}退出登录。", user.getPrincipal());
    	}
        return "redirect:/";//返回到根目录
    }
    
    //@RequiresRoles("admin")
    @RequestMapping(value="/admin/count",method=RequestMethod.GET)
    public ModelAndView userCount() {
        Long count = userService.count();
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("userCount", count);
        mv.setViewName("user/userCount");
        return mv;
    }

    //@RequiresRoles("admin")
    @RequestMapping(value="/admin/list",method=RequestMethod.GET)
    public ModelAndView getUserlist(@RequestParam(value="account", defaultValue="") String account) throws Exception{
        
        ModelAndView mv = new ModelAndView();
        List<SysUser> userList = userService.findByAccountNickName(account);
        mv.addObject("userList", userList);
        mv.setViewName("user/list");
        return mv;
    }
    
    //@RequiresRoles("admin")
    @RequestMapping(value="/admin/all",method=RequestMethod.GET)
    public ModelAndView getUsers(Model model){
        
        ModelAndView mv = new ModelAndView();
        List<SysUser> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user/list");
        return mv;
    }
    
    
    @RequestMapping(value="/admin/add",method=RequestMethod.GET)
    public ModelAndView getAdd() throws Exception{
    	//测试异常处理
        if(true) throw new SQLException("SQL异常");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/add");
        mv.addObject(new SysUser());
        return mv;
    }
    
    @RequestMapping(value="/admin/add",method=RequestMethod.POST)
    public String add(@Valid @ModelAttribute("sysUser") SysUser sysUser, BindingResult result){
    	if(result.hasErrors()) {
            return "user/add";
        }
    	
        String encrypted = CipherUtil.simpleHash("md5", sysUser.getPasswd(), null, 2, true);
        sysUser.setPasswd(encrypted);
        userService.save(sysUser);
        return "redirect:/user/admin/list";
    }
    
    @RequestMapping(value="/show/{userid}",method=RequestMethod.GET)
    public ModelAndView show(@PathVariable Long userid){
    	SysUser userModel = userService.getOne(userid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userModel);
        mv.setViewName("user/detail");
        return mv;
    }
    
    //@RequiresRoles("admin")
    @RequestMapping(value="/admin/del/{userId}",method=RequestMethod.DELETE)
    public void del(@PathVariable Long userId){
    	SysUser sysUser = new SysUser();
    	sysUser.setId(userId);
        userService.delete(sysUser);
        
        //return "redirect:/user/admin/list";
    }
    
    @RequestMapping(value="/edit/{userid}",method=RequestMethod.GET)
    public ModelAndView getEdit(@PathVariable Long userid, Model model){
    	SysUser sysUser = userService.getOne(userid);
        model.addAttribute("sysUser", sysUser);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/edit");
        return mv;
    }
    
    @RequestMapping(value="/save/{userid}",method=RequestMethod.POST)
    public String saveEdit(@ModelAttribute("sysUser") SysUser userModel, @PathVariable int userid){
        //userService.attachClean(userModel);
    	userService.save(userModel);
        return "redirect:/user/admin/list";
    }
}
