package com.bit.common.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bit.acc.model.SysLog;
import com.bit.acc.model.SysUser;
import com.bit.acc.service.intfs.SysLogService;
import com.bit.common.util.IConstants;

@Aspect
@Component
public class LogAdvice{
	
	// 注入Service用于把日志保存到数据库
	@Resource(name="sysLogService")
	private SysLogService sysLogService;
	
	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	// Service层切点
	@Pointcut("@annotation(com.bit.common.log.ServiceLog)")
	public void serviceAspect() {
	}

	// Controller层切点
	@Pointcut("@annotation(com.bit.common.log.ControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()&&@annotation(log)")
	public void doBefore(JoinPoint joinPoint, ControllerLog log) {
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser user = (SysUser) session.getAttribute(IConstants.SHIRO_SESSION_USER);
		// 请求的IP
		String ip = session.getHost();
		try {
			// *========数据库日志=========*//
			/*Log log = SpringContextHolder.getBean("sysLogService");
			log.setDescription(getControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			log.setType("0");
			log.setRequestIp(ip);
			log.setExceptionCode(null);
			log.setExceptionDetail(null);
			log.setParams(null);
			log.setCreateBy(user);
			log.setCreateDate(DateUtil.getCurrentDate());
			// 保存数据库
			logService.add(log);*/
			SysLog sysLog = new SysLog();
			sysLog.setIp(ip);
			sysLog.setUserId(user==null ? null : user.getId());
			sysLog.setLog( log.value() );


            Class targetClass = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();

			sysLog.setEntityName(joinPoint.getTarget().getClass().getName());
			sysLog.setInstance(null);
			sysLog.setAttribute(null);
			sysLog.setOperate(joinPoint.getSignature().getName());
			sysLog.setOldValue(null);
			sysLog.setNewValue(null);
			sysLog.setOprtTime(new Date());
//			sysLogService.save(sysLog);
			
			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 异常通知 用于拦截controller层，记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterControllerThrowing(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e);
	}

	/**
	 * 异常通知 用于拦截service层，记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterServiceThrowing(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e);
	}
	
	private void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		//User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
		// 获取请求ip
		String ip = request.getRemoteAddr();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				params += String.valueOf(joinPoint.getArgs()[i]) + ";";
			}
		}
		/* ==========记录本地异常日志========== */
		logger.error("\r\n\t异常方法:{}\r\n\t方法参数:{}\r\n\t异常代码:{}\r\n\t异常信息:{}\r\n\t堆栈打印:{}",
				joinPoint.getTarget().getClass().getName() +"." + joinPoint.getSignature().getName(), 
				params,
				e.getClass().getName(),
				e.getMessage(),
				e.getStackTrace()
				);
		
	}

}
